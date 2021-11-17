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
import java.util.List;

@WebServlet("/info-reading-student")
public class InfoReadingStudent extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();


        DataDaoStudent dataDaoStudent = new DataDaoStudentImpl();
        String loginStudent = req.getParameter("loginStudent");

        Student student = dataDaoStudent.read(loginStudent);

        List<Student>studentList = dataDaoStudent.readAll();
        try {
            writer.println("<p>Id student :" + student.getId() + "</p>");
            writer.println("<p>Login student :" + student.getName() + "</p>");
            writer.println("<p>Password student :" + student.getPassword() + "</p>");
            writer.println("<p>Name student :" + student.getName() + "</p>");
            writer.println("<p>Age student :" + student.getName() + "</p>");
            writer.println("<p>Role  :" + student.getRole() + "</p>");
            writer.println("<p>Grade  :" + student.getGrade() + "</p>");
            writer.println("<a href=\"admin\">Back to main menu</a>");

            for (Student s:studentList) {
                writer.println(s);
            }
        }
        finally {
            writer.close();
        }

    }
}
