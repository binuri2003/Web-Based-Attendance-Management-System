package com.attendance.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentController {

<<<<<<< HEAD

    @GetMapping("/student/dashboard")
    public String studentDashboard() {

        return "student/dashboard";

=======
    @GetMapping("/student/dashboard")
    public String studentDashboard() {
        return "student/dashboard";
>>>>>>> main
    }

}