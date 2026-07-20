package com.attendance.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.attendance.demo.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    // Search by registration number, student name or username
    List<Student> findByRegistrationNoContainingIgnoreCaseOrStudentNameContainingIgnoreCaseOrUsernameContainingIgnoreCase(
            String registrationNo,
            String studentName,
            String username);

}
