package com.sport.util;

import com.sport.dao.jdbc.SecurityConfig;
import com.sport.model.AbstractUser;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

public class ApplicationUtils {
    private static int REDIRECT_ID = 0;

    private static final Map<Integer, String> ID_URI_MAP = new HashMap<Integer, String>();
    private static final Map<String, Integer> URI_ID_MAP = new HashMap<String, Integer>();

    public static void storeLoginedUser(HttpSession session, AbstractUser loginedUser) {
        session.setAttribute("loginedUser", loginedUser);
    }

    public static AbstractUser getLoginedUser(HttpSession session) {
        AbstractUser loginedUser = (AbstractUser) session.getAttribute("loginedUser");
        return loginedUser;
    }

    public static int storeRedirectAfterLoginUrl(HttpSession session, String requestUri) {
        Integer id = URI_ID_MAP.get(requestUri);
        if (id == null) {
            id = REDIRECT_ID++;
            URI_ID_MAP.put(requestUri, id);
            ID_URI_MAP.put(id, requestUri);
            return id;
        }
        return id;
    }

    public static String getRedirectAfterLoginUrl(HttpSession session, int redirectId) {
        String url = ID_URI_MAP.get(redirectId);
        if (url != null) {
            return url;
        }
        return null;
    }

    public static boolean isSecurityPage(HttpServletRequest request) {
        String urlPattern = getUrlPattern(request);
        Set<String> roles = SecurityConfig.getRoleUsers();
        for (String role : roles) {
            List<String> urlPatterns = SecurityConfig.getUrlUserForRole(role);
            if (urlPatterns != null && urlPatterns.contains(urlPattern)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasPermission(HttpServletRequest request) {
        String urlPattern = getUrlPattern(request);
        Set<String> allRoles = SecurityConfig.getRoleUsers();
        for (String role : allRoles) {
            if (!request.isUserInRole(role)) {
                continue;
            }
            List<String> urlPatterns = SecurityConfig.getUrlUserForRole(role);
            if (urlPatterns != null && urlPatterns.contains(urlPattern)) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasUrlPattern(ServletContext servletContext, String urlPattern) {
        Map<String, ? extends ServletRegistration> map = servletContext.getServletRegistrations();
        for (String servletName : map.keySet()) {
            ServletRegistration sr = map.get(servletName);
            Collection<String> mappings = sr.getMappings();
            if (mappings.contains(urlPattern)) {
                return true;
            }
        }
        return false;
    }

    private static String getUrlPattern(HttpServletRequest request) {
        ServletContext servletContext = request.getServletContext();
        String servletPath = request.getServletPath();
        String pathInfo = request.getPathInfo();
        String urlPattern = null;
        if (pathInfo != null) {
            urlPattern = servletPath + "/*";
            return urlPattern;
        }
        urlPattern = servletPath;
        boolean has = hasUrlPattern(servletContext, urlPattern);
        if (has) {
            return urlPattern;
        }
        int i = servletPath.lastIndexOf('.');
        if (i != -1) {
            String ext = servletPath.substring(i + 1);
            urlPattern = "*." + ext;
            has = hasUrlPattern(servletContext, urlPattern);
            if (has) {
                return urlPattern;
            }
        }
        return "/";
    }

}
