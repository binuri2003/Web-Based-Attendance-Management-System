package com.attendance.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private ClassEntity studentClass;

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

    public ClassEntity getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(ClassEntity studentClass) {
        this.studentClass = studentClass;
    }

    @Override
    public String getDashboard() {
        return "student/dashboard";
    }
}