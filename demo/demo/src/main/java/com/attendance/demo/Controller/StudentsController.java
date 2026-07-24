package com.attendance.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping
    public List<StudentResponse> getAllStudents() {

        return studentService.getAllStudents();

    }

    @PostMapping
    public Student addStudent(
            @RequestBody StudentRequest request) {

        return studentService.addStudent(request);

    }

    @PutMapping("/{id}")
    public Student updateStudent(
            @PathVariable Integer id,
            @RequestBody StudentRequest request) {

        return studentService.updateStudent(
                id,
                request);

    }

    @DeleteMapping("/{id}")
    public String deleteStudent(
            @PathVariable Integer id) {

        studentService.deleteStudent(id);

        return "Student deleted successfully";

    }

    @GetMapping("/search")
    public List<StudentResponse> searchStudents(
            @RequestParam String keyword) {

        return studentService.searchStudents(
                keyword);

    }

    @GetMapping("/search/class")
    public List<StudentResponse> searchByClass(
            @RequestParam String className) {

        return studentService.searchByClass(
                className);

    }

    @GetMapping("/search/stream")
    public List<StudentResponse> searchByStream(
            @RequestParam String stream) {

        return studentService.searchByStream(
                stream);

    }

}