package com.attendance.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.attendance.demo.dto.StudentResponse;
import com.attendance.demo.entity.Student;
import com.attendance.demo.repository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // Get all students
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

}