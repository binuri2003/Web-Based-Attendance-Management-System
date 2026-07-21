package com.attendance.demo.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.attendance.demo.dto.StudentRequest;
import com.attendance.demo.dto.StudentResponse;
import com.attendance.demo.entity.Student;
import com.attendance.demo.service.StudentService;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*")
public class StudentsController {

    @Autowired
    private StudentService studentService;

    // ============================
    // Get All Students
    // ============================

    @GetMapping
    public List<StudentResponse> getAllStudents() {

        return studentService.getAllStudents();

    }

    // ============================
    // Add Student
    // ============================

    @PostMapping
    public Student addStudent(@RequestBody StudentRequest request) {

        return studentService.addStudent(request);

    }

    // ============================
    // Search Students
    // ============================

    @GetMapping("/search")
    public List<StudentResponse> searchStudents(
            @RequestParam String keyword) {

        return studentService.searchStudents(keyword);

    }

}