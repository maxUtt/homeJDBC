package com.sport.model;

public class Student extends AbstractUser {
    private int grade;

    public Student(int id, String login, String password, String name, int age, String role, int grade) {
        super(id, login, password, name, age, role);
        this.grade = grade;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return super.toString() + "Student{" +
                "grade=" + grade +
                '}';
    }
}
