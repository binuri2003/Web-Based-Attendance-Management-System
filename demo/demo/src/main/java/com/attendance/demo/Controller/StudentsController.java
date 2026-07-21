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



    // GET ALL

    @GetMapping
    public List<StudentResponse> getAllStudents(){

        return studentService.getAllStudents();

    }




    // ADD STUDENT

    @PostMapping
    public Student addStudent(
            @RequestBody StudentRequest request){

        return studentService.addStudent(request);

    }




    // SEARCH NAME / REGISTRATION / USERNAME

    @GetMapping("/search")
    public List<StudentResponse> searchStudents(
            @RequestParam String keyword){

        return studentService.searchStudents(keyword);

    }





    // SEARCH CLASS

    @GetMapping("/search/class")
    public List<StudentResponse> searchByClass(
            @RequestParam String className){

        return studentService.searchByClass(className);

    }




    // SEARCH STREAM

    @GetMapping("/search/stream")
    public List<StudentResponse> searchByStream(
            @RequestParam String stream){

        return studentService.searchByStream(stream);

    }


}