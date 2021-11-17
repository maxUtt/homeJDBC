package com.sport.model;

public class Teacher extends AbstractUser {
    private int salary;

    public Teacher(int id, String login, String password, String name, int age, String role, int salary) {
        super(id, login, password, name, age, role);
        this.salary = salary;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
