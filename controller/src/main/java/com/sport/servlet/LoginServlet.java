package com.sport.servlet;

import com.sport.dao.jdbc.DataDao;
import com.sport.model.AbstractUser;
import com.sport.util.ApplicationUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = "/login.jsp";
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        AbstractUser userAccount = DataDao.finsUser(userName, password);

        if (userAccount == null) {
            String errorMassage = "Invalid userName or password";
            req.setAttribute("errorMessage", errorMassage);
            RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/login.jsp");
            requestDispatcher.forward(req, resp);
            return;
        }
        ApplicationUtils.storeLoginedUser(req.getSession(), userAccount);
        int redirectId = -1;
        try {
            redirectId = Integer.parseInt(req.getParameter("redirectId"));
        } catch (Exception e) {

        }
        String requestUri = ApplicationUtils.getRedirectAfterLoginUrl(req.getSession(), redirectId);
        if (requestUri != null) {
            resp.sendRedirect(requestUri);
        } else {
            resp.sendRedirect(req.getContextPath() + "/userInfo.jsp");
        }
    }
}
