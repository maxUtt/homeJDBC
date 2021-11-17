package com.sport.servlet.admin;

import com.sport.dao.jdbc.jdbc.DataDaoStudent;
import com.sport.dao.jdbc.jdbc.DataDaoStudentImpl;
import com.sport.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/info-updating-student")
public class InfoUpdatingStudentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        String oldNameStudent = req.getParameter("loginStudent");
        String newLoginStudent = req.getParameter("newLoginStudent");
        String newPasswordStudent = req.getParameter("newPasswordStudent");
        String newNameStudent = req.getParameter("newNameStudent");
        int newAgeStudent = Integer.parseInt(req.getParameter("newAgeStudent"));
        String role = req.getParameter("role");
        int newGrade = Integer.parseInt(req.getParameter("newGrade"));

        DataDaoStudent dataDaoStudent = new DataDaoStudentImpl();
        Student newStudent = new Student(0, newLoginStudent, newPasswordStudent,newNameStudent, newAgeStudent, role, newGrade);
        dataDaoStudent.update(oldNameStudent, newStudent);

        try {
            writer.println("<p>We update student. His new data is:<p/>");
            writer.println("<p>" + newStudent + "</p>");
            writer.println("<a href=\"admin\">Back to main menu</a>");
        }
        finally {
            writer.close();
        }
    }
}
