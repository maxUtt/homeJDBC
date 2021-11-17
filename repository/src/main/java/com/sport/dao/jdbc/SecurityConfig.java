package com.sport.dao.jdbc;

import java.util.*;

public class SecurityConfig {
    protected static final String ROLE_ADMIN = "ADMIN";
    protected static final String ROLE_TEACHER = "TEACHER";
    protected static final String ROLE_STUDENT = "STUDENT";

    private static final Map<String, List<String>> MAP_SECURITY_CONFIG = new HashMap<>();

    static {
        initUsers();
    }

    private static void initUsers() {
        List<String> urlAdmin = new ArrayList<>();
        urlAdmin.add("/userInfo");
        urlAdmin.add("/admin");
        MAP_SECURITY_CONFIG.put(ROLE_ADMIN, urlAdmin);
        List<String>urlTeacher = new ArrayList<>();
        urlTeacher.add("/userInfo");
        urlTeacher.add("/teacher");
        MAP_SECURITY_CONFIG.put(ROLE_TEACHER, urlTeacher);
        List<String>urlStudent = new ArrayList<>();
        urlStudent.add("/userInfo");
        urlStudent.add("/student");
        MAP_SECURITY_CONFIG.put(ROLE_STUDENT, urlStudent);
    }

    public static Set<String> getRoleUsers() {
        return MAP_SECURITY_CONFIG.keySet();
    }

    public static List<String> getUrlUserForRole(String urlUser){
        return MAP_SECURITY_CONFIG.get(urlUser);
    }
}
