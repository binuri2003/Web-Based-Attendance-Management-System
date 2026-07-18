package com.attendance.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/lecturer")
public class LecturerController {

    @GetMapping("/create_session")
    public String createSessionPage() {

        return "lecturer/create_session";

    }

    @PostMapping("/start-session")
    public String startSession(

            @RequestParam int subjectId,
            @RequestParam int classId,
            Model model) {

        // Backend will generate session and save it later

        return "lecturer/create_session";

    }

    @PostMapping("/end-session")
    public String endSession(

            @RequestParam int sessionId) {

        // Close attendance session

        return "redirect:/lecturer/create_session";

    }

}