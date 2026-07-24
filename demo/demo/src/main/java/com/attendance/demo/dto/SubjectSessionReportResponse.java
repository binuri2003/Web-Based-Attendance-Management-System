package com.attendance.demo.dto;

public class SubjectSessionReportResponse {

    private String code;

    private String name;

    private String lecturer;

    private long count;

    public SubjectSessionReportResponse() {

    }

    public SubjectSessionReportResponse(
            String code,
            String name,
            String lecturer,
            long count) {

        this.code = code;
        this.name = name;
        this.lecturer = lecturer;
        this.count = count;

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

}
