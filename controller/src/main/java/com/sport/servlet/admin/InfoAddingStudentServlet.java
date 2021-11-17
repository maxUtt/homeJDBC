package com.sport.servlet.admin;

import com.sport.dao.jdbc.jdbc.DataDaoStudent;
import com.sport.dao.jdbc.jdbc.DataDaoStudentImpl;
import com.sport.model.AbstractUser;
import com.sport.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/info-adding-student")
public class InfoAddingStudentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        String login = req.getParameter("loginStudent");
        String password = req.getParameter("passwordStudent");
        String name = req.getParameter("nameStudent");
        String age = req.getParameter("ageStudent");
        String role = req.getParameter("role");
        String grade = req.getParameter("grade");

        int ageInt = Integer.parseInt(age);
        int gradeInt = Integer.parseInt(grade);

        AbstractUser user = new Student(0, login, password, name, ageInt, role, gradeInt);
        DataDaoStudent dao = new DataDaoStudentImpl();
        dao.add((Student) user);

        try {
            writer.println("<p>Login: " + login + "</p>");
            writer.println("<p>Name: " + name + "</p>");
            writer.println("<p>Age: " + age + "</p>");
            writer.println("<p>Role: " + role + "</p>");
            writer.println("<p>Grade: " + grade + "</p>");
            writer.println("<a href=\"admin\">Back to main menu</a>");
        } finally {
            writer.close();
        }


    }
}
