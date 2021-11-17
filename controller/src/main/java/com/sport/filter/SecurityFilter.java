package com.sport.filter;

import com.sport.model.AbstractUser;
import com.sport.request.UserRoleRequestWrapper;
import com.sport.util.ApplicationUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class SecurityFilter implements Filter {

    public SecurityFilter() {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String servletPath = request.getServletPath();

        AbstractUser loginedUser = ApplicationUtils.getLoginedUser(request.getSession());

        if (servletPath.equals("/login")) {
            chain.doFilter(request, response);
            return;
        }
        HttpServletRequest wrapRequest = request;

        if (loginedUser != null) {
            String userName = loginedUser.getLogin();
            String roles = loginedUser.getRole();
            wrapRequest = new UserRoleRequestWrapper(userName, roles, request);
        }

        if (ApplicationUtils.isSecurityPage(request)) {

            if (loginedUser == null) {
                String requestUri = request.getRequestURI();
                int redirectId = ApplicationUtils.storeRedirectAfterLoginUrl(request.getSession(), requestUri);
                response.sendRedirect(wrapRequest.getContextPath() + "/login?redirectId=" + redirectId);
                return;
            }

            boolean hasPermission = ApplicationUtils.hasPermission(wrapRequest);
            if (!hasPermission) {
                RequestDispatcher dispatcher //
                        = request.getServletContext().getRequestDispatcher("/access.jsp");
                dispatcher.forward(request, response);
                return;
            }
        }
        chain.doFilter(wrapRequest, response);
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {

    }

}
