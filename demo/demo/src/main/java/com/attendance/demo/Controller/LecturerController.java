package com.attendance.demo.Controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.attendance.demo.entity.AttendanceSession;
import com.attendance.demo.entity.ClassEntity;
import com.attendance.demo.entity.Lecturer;
import com.attendance.demo.entity.Subject;
import com.attendance.demo.repository.ClassRepository;
import com.attendance.demo.repository.LecturerRepository;
import com.attendance.demo.repository.SubjectRepository;
import com.attendance.demo.service.AttendanceSessionService;

@Controller
@RequestMapping("/lecturer")
public class LecturerController {

    @Autowired
    private AttendanceSessionService attendanceSessionService;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private LecturerRepository lecturerRepository;

    @GetMapping("/dashboard")
    public String lecturerDashboard() {
        return "lecturer/dashboard";
    }

    @GetMapping("/create_session")
    public String createSessionPage() {

        return "lecturer/create_session";

    }

    @PostMapping("/start-session")
    public String startSession(
            @RequestParam int subjectId,
            @RequestParam int classId,
            Model model) {

        Subject subject = subjectRepository.findById(subjectId).orElse(null);
        ClassEntity classEntity = classRepository.findById(classId).orElse(null);

        if (subject == null || classEntity == null) {
            model.addAttribute("error", "Invalid subject or class.");
            return "lecturer/create_session";
        }

        AttendanceSession session = new AttendanceSession();

        session.setSubject(subject);
        session.setClassEntity(classEntity);

        // Temporary lecturer
        Lecturer lecturer = lecturerRepository.findById(3).orElse(null);
        session.setLecturer(lecturer);

        session.setSessionCode(generateSessionCode());

        session.setSessionDate(new java.sql.Date(System.currentTimeMillis()));

        java.sql.Time now = new java.sql.Time(System.currentTimeMillis());
        session.setStartTime(now);

        attendanceSessionService.saveSession(session);

        model.addAttribute("attendanceSession", session);

        return "lecturer/create_session";
    }

    @PostMapping("/end-session")
    public String endSession(

            @RequestParam int sessionId) {

        // Close attendance session

        return "redirect:/lecturer/create_session";

    }

    private String generateSessionCode() {

        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        StringBuilder code = new StringBuilder();

        Random random = new Random();

        for (int i = 0; i < 6; i++) {
            code.append(chars.charAt(random.nextInt(chars.length())));
        }

        return code.toString();
    }

    @GetMapping("/manage_attendance")
    public String manageAttendance() {
        return "lecturer/manage_attendance";
    }

    @GetMapping("/view_attendance")
    public String viewAttendance() {
        return "lecturer/view_attendance";
    }

    @GetMapping("/student_history")
    public String studentHistory() {
        return "lecturer/student_history";
    }

}