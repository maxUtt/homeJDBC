package com.sport.servlet.admin;

import com.sport.dao.jdbc.jdbc.DataDaoStudent;
import com.sport.dao.jdbc.jdbc.DataDaoStudentImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/info-delete-student")
public class InfoDeletingStudentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String loginStudent = req.getParameter("loginStudent");
        DataDaoStudent dataDaoStudent = new DataDaoStudentImpl();
        boolean isDelete = dataDaoStudent.delete(loginStudent);

        PrintWriter writer = resp.getWriter();
        resp.setContentType("text/html");

        try {
            if (isDelete) {
                writer.println("<p> Student with login: " + loginStudent + " delete</p>");
            } else {
                writer.println("<p> Student can not delete with login: " + loginStudent + "try again </p>");
            }
            writer.println("<a href=\"admin\">Back to main menu</a>");
        } finally {
            writer.close();
        }

    }
}
