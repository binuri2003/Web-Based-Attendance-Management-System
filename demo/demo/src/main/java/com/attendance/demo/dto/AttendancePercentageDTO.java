package com.attendance.demo.dto;

public class AttendancePercentageDTO {

    private String subjectName;
    private int presentCount;
    private int totalSessions;
    private double percentage;

    public AttendancePercentageDTO() {
    }

    public AttendancePercentageDTO(String subjectName, int presentCount, int totalSessions, double percentage) {
        this.subjectName = subjectName;
        this.presentCount = presentCount;
        this.totalSessions = totalSessions;
        this.percentage = percentage;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getPresentCount() {
        return presentCount;
    }

    public void setPresentCount(int presentCount) {
        this.presentCount = presentCount;
    }

    public int getTotalSessions() {
        return totalSessions;
    }

    public void setTotalSessions(int totalSessions) {
        this.totalSessions = totalSessions;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
}