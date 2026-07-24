package com.attendance.demo.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import com.attendance.demo.dto.LoginRequest;
import com.attendance.demo.entity.User;
import com.attendance.demo.service.LoginService;

import jakarta.servlet.http.HttpSession;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginRequest request,
            HttpSession session) {

        User user = loginService.login(
                request.getUsername(),
                request.getPassword());

        Map<String, String> response = new HashMap<>();

        if (user != null) {

            session.setAttribute("loggedUser", user);

            response.put("role", user.getRole());

        } else {

            response.put("role", "invalid");

        }

        return response;
    }
}