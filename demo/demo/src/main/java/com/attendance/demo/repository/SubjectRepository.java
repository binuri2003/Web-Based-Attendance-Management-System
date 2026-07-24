package com.attendance.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.attendance.demo.entity.Lecturer;
import com.attendance.demo.entity.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    List<Subject> findByLecturer(Lecturer lecturer);

    List<Subject> findBySubjectNameContainingIgnoreCase(String subjectName);

}