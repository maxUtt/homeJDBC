package com.sport.model;

public class Admin extends AbstractUser {
    public Admin(int id, String login, String password, String name, int age, String role) {
        super(id, login, password, name, age, role);
    }
}
