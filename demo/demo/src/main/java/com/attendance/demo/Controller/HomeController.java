package com.attendance.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // ==========================
    // Login Page
    // ==========================
    @GetMapping("/")
    public String login() {
        return "login";
    }

    // ==========================
    // Logout
    // ==========================
    @GetMapping("/logout")
    public String logout() {
        return "login";
    }

}