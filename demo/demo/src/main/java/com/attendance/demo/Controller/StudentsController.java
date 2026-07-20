package com.attendance.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentsController {

    @GetMapping("/students")
    public String studentsPage() {
        return "admin/students";
    }

}