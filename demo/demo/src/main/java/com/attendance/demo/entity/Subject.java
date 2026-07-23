package com.attendance.demo.entity;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="subject")
public class Subject {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="subject_id")
    private int subjectId;


    @Column(name="subject_code")
    private String subjectCode;


    @Column(name="subject_name")
    private String subjectName;


    private int credits;



    @ManyToOne
    @JoinColumn(name="lecturer_id")
    private Lecturer lecturer;



    public Subject(){

    }



    public int getSubjectId() {
        return subjectId;
    }


    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }



    public String getSubjectCode() {
        return subjectCode;
    }


    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }



    public String getSubjectName() {
        return subjectName;
    }


    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }



    public int getCredits() {
        return credits;
    }


    public void setCredits(int credits) {
        this.credits = credits;
    }



    public Lecturer getLecturer() {
        return lecturer;
    }


    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

}