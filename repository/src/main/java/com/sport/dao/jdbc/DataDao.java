package com.sport.dao.jdbc;

import com.sport.dao.jdbc.jdbc.DataDaoStudent;
import com.sport.dao.jdbc.jdbc.DataDaoStudentImpl;
import com.sport.model.AbstractUser;
import com.sport.model.Admin;
import com.sport.model.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataDao {
    private static final Map<String, AbstractUser> MAP_USER = new HashMap<>();

    static {
        initMapUsers();
    }

    private static void initMapUsers() {
        AbstractUser admin = new Admin(-1, "admin", "222", "Alex", 25, SecurityConfig.ROLE_ADMIN);
        MAP_USER.put(admin.getLogin(), admin);

        DataDaoStudent student = new DataDaoStudentImpl();
        List<Student> studentList = student.readAll();
        for (Student s : studentList) {
            MAP_USER.put(s.getLogin(), new Student(s.getId(), s.getLogin(), s.getPassword(), s.getName(), s.getAge(), SecurityConfig.ROLE_STUDENT, s.getGrade()));
        }
    }

    public static AbstractUser finsUser(String login, String password) {
        AbstractUser user = MAP_USER.get(login);
        if (user != null && user.getPassword().equals(password))
            return user;
        else
            return null;
    }
}
