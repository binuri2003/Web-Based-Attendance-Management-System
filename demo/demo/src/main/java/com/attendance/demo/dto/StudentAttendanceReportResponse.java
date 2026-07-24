package com.attendance.demo.dto;


public class StudentAttendanceReportResponse {


    private String regNo;

    private String name;

    private String subject;

    private int present;

    private int absent;

    private int total;

    private double percentage;



    public StudentAttendanceReportResponse() {

    }



    public StudentAttendanceReportResponse(
            String regNo,
            String name,
            String subject,
            int present,
            int absent,
            int total,
            double percentage
    ) {

        this.regNo = regNo;
        this.name = name;
        this.subject = subject;
        this.present = present;
        this.absent = absent;
        this.total = total;
        this.percentage = percentage;

    }



    public String getRegNo() {
        return regNo;
    }


    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }



    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }



    public String getSubject() {
        return subject;
    }


    public void setSubject(String subject) {
        this.subject = subject;
    }



    public int getPresent() {
        return present;
    }


    public void setPresent(int present) {
        this.present = present;
    }



    public int getAbsent() {
        return absent;
    }


    public void setAbsent(int absent) {
        this.absent = absent;
    }



    public int getTotal() {
        return total;
    }


    public void setTotal(int total) {
        this.total = total;
    }



    public double getPercentage() {
        return percentage;
    }


    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

}