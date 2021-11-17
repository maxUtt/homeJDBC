package com.sport.dao.jdbc.jdbc;

import com.sport.model.AbstractUser;
import com.sport.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataDaoStudentImpl implements DataDaoStudent {
    private static final Logger log = LoggerFactory.getLogger(DataDaoStudentImpl.class);

    @Override
    public boolean add(Student student) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            log.error("Can not find driver postgres at method Add DataDaoStudentImpl");
        }
        try {
            String sql = "INSERT INTO Students (Login, Password, Name, Age, Role, Grade) Values (?,?,?,?,?,?)";
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/sporting", "postgres", "postgres");
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, student.getLogin());
            statement.setString(2, student.getPassword());
            statement.setString(3, student.getName());
            statement.setInt(4, student.getAge());
            statement.setString(5, student.getRole());
            statement.setInt(6, student.getGrade());
            statement.executeUpdate();

            statement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("SQL Exception at method Add DataDaoStudentImpl");
        }
        return false;
    }

    @Override
    public Student read(String login) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            log.error("Can not find driver postgres at method Read DataDaoStudentImpl");
        }
        try {
            String sqlCode = "SELECT Id, Login, Password, Name, Age, Role, Grade FROM Students WHERE Login = ?";
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/sporting", "postgres", "postgres");

            PreparedStatement statement = connection.prepareStatement(sqlCode);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idStudent = resultSet.getInt("Id");
                String loginStudent = resultSet.getString("Login");
                String passwordStudent = resultSet.getString("Password");
                String nameStudent = resultSet.getString("Name");
                int ageStudent = resultSet.getInt("Age");
                String roleStudent = resultSet.getString("Role");
                int gradeStudent = resultSet.getInt("Grade");

                AbstractUser student = new Student(idStudent, loginStudent, passwordStudent, nameStudent, ageStudent, roleStudent, gradeStudent);
                return (Student) student;
            }


        } catch (SQLException e) {
            log.error("SQL Exception at method Read DataDaoStudentImpl");
        }
        return null;
    }

    @Override
    public boolean update(String login, Student newStudent) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            log.error("Can not find driver postgres at method Update DataDaoStudentImpl");
        }
        try {
            String sqlCode = "UPDATE Students SET Login = ?, Password = ?, Name = ?, Age = ?, Grade = ? WHERE Login = ?";
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/sporting", "postgres", "postgres");

            PreparedStatement statement = connection.prepareStatement(sqlCode);

            statement.setString(1, newStudent.getLogin());
            statement.setString(2, newStudent.getPassword());
            statement.setString(3, newStudent.getName());
            statement.setInt(4, newStudent.getAge());
            statement.setInt(5, newStudent.getGrade());
            statement.setString(6, login);
            statement.executeUpdate();

            return true;

        } catch (SQLException e) {
            log.error("SQL Exception at method Update DataDaoStudentImpl");
        }
        return false;
    }

    @Override
    public boolean delete(String login) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            log.error("Can not find driver postgres at method Delete DataDaoStudentImpl");
        }
        try {
            String sqlCode = "DELETE FROM Students WHERE Login = ?";
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/sporting", "postgres", "postgres");
            PreparedStatement statement = connection.prepareStatement(sqlCode);
            statement.setString(1, login);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("SQL Exception at method Delete DataDaoStudentImpl");
        }
        return false;
    }

    @Override
    public List<Student> readAll() {
        List<Student> studentList = new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            log.error("Can not find driver postgres at method ReadAll DataDaoStudentImpl");
        }
        try {
            String sqlCode = "SELECT Id, Login, Password, Name, Age, Role, Grade FROM Students";
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/sporting", "postgres", "postgres");
            PreparedStatement statement = connection.prepareStatement(sqlCode);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("Id");
                String login = resultSet.getString("Login");
                String password = resultSet.getString("Password");
                String name = resultSet.getString("Name");
                int age = resultSet.getInt("Age");
                String role = resultSet.getString("Role");
                int grade = resultSet.getInt("Grade");
                studentList.add(new Student(id,login,password,name,age,role,grade));
            }
            return studentList;
        }
        catch (SQLException e){
            e.printStackTrace();
            log.error("SQL Exception at method ReadAll DataDaoStudentImpl");
        }
        return null;
    }
}
