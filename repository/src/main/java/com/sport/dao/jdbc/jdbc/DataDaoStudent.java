package com.sport.dao.jdbc.jdbc;

import com.sport.model.Student;

import java.util.List;

public interface DataDaoStudent {

    boolean add(Student student);

    Student read(String login);

    boolean update(String login, Student newStudent);

    boolean delete(String login);

    List<Student> readAll();

}
