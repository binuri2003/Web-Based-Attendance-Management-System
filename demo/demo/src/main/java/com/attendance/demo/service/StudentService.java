package com.attendance.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.attendance.demo.dto.StudentRequest;
import com.attendance.demo.dto.StudentResponse;
import com.attendance.demo.entity.ClassEntity;
import com.attendance.demo.entity.Role;
import com.attendance.demo.entity.Student;
import com.attendance.demo.repository.ClassRepository;
import com.attendance.demo.repository.StudentRepository;
import com.attendance.demo.repository.UserRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClassRepository classRepository;

    // ============================
    // Get All Students
    // ============================

    public List<StudentResponse> getAllStudents() {

        List<Student> students = studentRepository.findAll();

        List<StudentResponse> responseList = new ArrayList<>();

        for (Student student : students) {

            StudentResponse response = new StudentResponse();

            response.setStudentId(student.getUserId());
            response.setUsername(student.getUsername());
            response.setRegistrationNo(student.getRegistrationNo());
            response.setStudentName(student.getStudentName());
            response.setEmail(student.getEmail());

            if (student.getStudentClass() != null) {
                response.setClassName(student.getStudentClass().getClassName());
            } else {
                response.setClassName("Not Assigned");
            }

            responseList.add(response);
        }

        return responseList;

    }

    // ============================
    // Search Students
    // ============================

    public List<StudentResponse> searchStudents(String keyword) {

        List<Student> students =
                studentRepository
                .findByRegistrationNoContainingIgnoreCaseOrStudentNameContainingIgnoreCaseOrUsernameContainingIgnoreCase(
                        keyword,
                        keyword,
                        keyword);

        List<StudentResponse> responseList = new ArrayList<>();

        for (Student student : students) {

            StudentResponse response = new StudentResponse();

            response.setStudentId(student.getUserId());
            response.setUsername(student.getUsername());
            response.setRegistrationNo(student.getRegistrationNo());
            response.setStudentName(student.getStudentName());
            response.setEmail(student.getEmail());

            if (student.getStudentClass() != null) {
                response.setClassName(student.getStudentClass().getClassName());
            } else {
                response.setClassName("Not Assigned");
            }

            responseList.add(response);

        }

        return responseList;

    }

    // ============================
    // Add Student
    // ============================

    public Student addStudent(StudentRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {

            throw new RuntimeException("Username already exists");

        }

        if (studentRepository.existsByRegistrationNo(request.getRegistrationNo())) {

            throw new RuntimeException("Registration number already exists");

        }

        Student student = new Student();

        // USER TABLE

        student.setUsername(request.getUsername());
        student.setPassword(request.getPassword());
        student.setRole(Role.Student);

        // STUDENT TABLE

        student.setRegistrationNo(request.getRegistrationNo());
        student.setStudentName(request.getStudentName());
        student.setEmail(request.getEmail());

        // CLASS

        if (request.getClassId() != null) {

            ClassEntity classEntity =
                    classRepository.findById(request.getClassId())
                    .orElseThrow(() ->
                            new RuntimeException("Class not found"));

            student.setStudentClass(classEntity);

        }

        return studentRepository.save(student);

    }

}