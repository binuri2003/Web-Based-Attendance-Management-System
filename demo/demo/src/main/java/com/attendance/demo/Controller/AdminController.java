package com.attendance.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    // ==========================
    // Dashboard
    // ==========================
    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "admin/dashboard";
    }

    // ==========================
    // Manage Students
    // ==========================
    @GetMapping("/admin/students")
    public String manageStudents() {
        return "admin/students";
    }

    // ==========================
    // Manage Lecturers
    // ==========================
    @GetMapping("/admin/lecturers")
    public String manageLecturers() {
        return "admin/lecturers";
    }

    // ==========================
    // Manage Subjects
    // ==========================
    @GetMapping("/admin/subjects")
    public String manageSubjects() {
        return "admin/subjects";
    }

    // ==========================
    // Manage Classes
    // ==========================
    @GetMapping("/admin/classes")
    public String manageClasses() {
        return "admin/classes";
    }

    // ==========================
    // Reports
    // ==========================
    @GetMapping("/admin/reports")
    public String manageReports() {
        return "admin/reports";
    }

}