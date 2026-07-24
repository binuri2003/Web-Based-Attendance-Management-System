package com.attendance.demo.dto;

public class SubjectAttendanceReportResponse {

    private String name;

    private String reg;

    private int present;

    private int absent;

    private double percentage;

    public SubjectAttendanceReportResponse() {

    }

    public SubjectAttendanceReportResponse(
            String name,
            String reg,
            int present,
            int absent,
            double percentage) {

        this.name = name;
        this.reg = reg;
        this.present = present;
        this.absent = absent;
        this.percentage = percentage;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReg() {
        return reg;
    }

    public void setReg(String reg) {
        this.reg = reg;
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

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

}
