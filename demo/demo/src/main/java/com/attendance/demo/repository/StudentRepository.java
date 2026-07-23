package com.attendance.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.attendance.demo.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {


    boolean existsByRegistrationNo(String registrationNo);

    Student findByUserId(Integer userId);


    List<Student> findByRegistrationNoContainingIgnoreCaseOrStudentNameContainingIgnoreCaseOrUsernameContainingIgnoreCase(
            String registrationNo,
            String studentName,
            String username
    );



    List<Student> findByStudentClass_ClassNameContainingIgnoreCase(
            String className
    );



    List<Student> findByStudentClass_StreamContainingIgnoreCase(
            String stream
    );

}