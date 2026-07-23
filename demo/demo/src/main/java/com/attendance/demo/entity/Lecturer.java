package com.attendance.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "lecturer")
@PrimaryKeyJoinColumn(name = "lecturer_id")

public class Lecturer extends User {

    @Column(name = "lecturer_name")
    private String lecturerName;

    @Column(name="email")
private String email;

    public Lecturer() {

        this.role = Role.Lecturer;

    }

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getDashboard() {

        return "lecturer/dashboard";

    }

}