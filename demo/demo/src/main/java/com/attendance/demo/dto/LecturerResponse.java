package com.attendance.demo.dto;


public class LecturerResponse {


    private Integer lecturerId;

    private String username;

    private String lecturerName;

    private String email;

    private String subjects;



    public LecturerResponse(){

    }



    public Integer getLecturerId() {
        return lecturerId;
    }


    public void setLecturerId(Integer lecturerId) {
        this.lecturerId = lecturerId;
    }



    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
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



    public String getSubjects() {
        return subjects;
    }


    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }


}