package com.attendance.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.attendance.demo.entity.StudentSubject;


public interface StudentSubjectRepository 
        extends JpaRepository<StudentSubject, Integer> {


    List<StudentSubject> findBySubject_SubjectId(Integer subjectId);


    List<StudentSubject> findByStudent_UserId(Integer userId);


}