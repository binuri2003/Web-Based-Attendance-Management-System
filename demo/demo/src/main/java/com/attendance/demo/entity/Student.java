package com.attendance.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "student")
@PrimaryKeyJoinColumn(name = "student_id")

public class Student extends User {

    @Column(name = "registration_no")
    private String registrationNo;

    @Column(name = "student_name")
    private String studentName;

    private String email;

    public Student() {

        this.role = Role.Student;

    }

    public String getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getDashboard() {

        return "student/dashboard";

    }

}