package com.attendance.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.attendance.demo.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    // Check duplicate registration number
    boolean existsByRegistrationNo(String registrationNo);

    // Search students
    List<Student> findByRegistrationNoContainingIgnoreCaseOrStudentNameContainingIgnoreCaseOrUsernameContainingIgnoreCase(
            String registrationNo,
            String studentName,
            String username);

}