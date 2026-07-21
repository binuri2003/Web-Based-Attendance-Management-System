package com.attendance.demo.dto;

public class StudentRequest {

    private String username;

    private String password;

    private String registrationNo;

    private String studentName;

    private String email;

    private Integer classId;


    public StudentRequest() {
    }


    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
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


    public Integer getClassId() {
        return classId;
    }


    public void setClassId(Integer classId) {
        this.classId = classId;
    }
}
