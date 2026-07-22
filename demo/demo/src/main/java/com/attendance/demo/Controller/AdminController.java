package com.attendance.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {


    @GetMapping("/admin/dashboard")
    public String adminDashboard() {

        return "admin/dashboard";

    }



    @GetMapping("/admin/students")
    public String manageStudents() {

        return "admin/students";

    }



    @GetMapping("/admin/lecturers")
    public String manageLecturers() {

        return "admin/lecturers";

    }



    @GetMapping("/admin/subjects")
    public String manageSubjects() {

        return "admin/subjects";

    }



    @GetMapping("/admin/classes")
    public String manageClasses() {

        return "admin/classes";

    }


}