package com.attendance.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentController {

    @GetMapping("/student/dashboard")
    public String studentDashboard() {
        return "student/dashboard";
    }

    @GetMapping("/student/enter_session_code")
    public String enterSessionCode() {
        return "student/enter_session_code";
    }

    @GetMapping("/student/attendance_history")
    public String attendanceHistory() {
        return "student/attendance_history";
    }

    @GetMapping("/student/attendance_percentage")
    public String attendancePercentage() {
        return "student/attendance_percentage";
    }

    @GetMapping("/student/attendance_history")
    public String attendanceHistory() {
        return "student/attendance_history";
    }

}