package com.attendance.demo.Controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;

import com.attendance.demo.entity.Attendance;
import com.attendance.demo.entity.AttendanceSession;
import com.attendance.demo.entity.AttendanceStatus;
import com.attendance.demo.entity.Student;
import com.attendance.demo.entity.User;
import com.attendance.demo.repository.StudentRepository;
import com.attendance.demo.service.AttendanceService;
import com.attendance.demo.service.AttendanceSessionService;
import jakarta.servlet.http.HttpSession;

@Controller
public class StudentController {

    @Autowired
    private AttendanceSessionService attendanceSessionService;

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private StudentRepository studentRepository;

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

    @PostMapping("/student/mark-attendance")
    public String markAttendance(@RequestParam String sessionCode,
            HttpSession httpSession,
            Model model) {

        AttendanceSession attendanceSession = attendanceSessionService.getSessionByCode(sessionCode);

        if (attendanceSession == null) {
            model.addAttribute("error", "Invalid session code.");
            return "student/enter_session_code";
        }

        if (attendanceSession.getEndTime() != null) {
            model.addAttribute("error", "This attendance session has already ended.");
            return "student/enter_session_code";
        }

        User loggedUser = (User) httpSession.getAttribute("loggedUser");

        System.out.println("Logged User: " + loggedUser);

        if (loggedUser == null) {
            model.addAttribute("error", "Please login again.");
            return "student/enter_session_code";
        }

        System.out.println("Logged User ID = " + loggedUser.getUserId());

        Student student = studentRepository.findByUserId(loggedUser.getUserId());

        System.out.println("Student = " + student);

        if (student == null) {
            model.addAttribute("error", "Student not found.");
            return "student/enter_session_code";
        }

        Optional<Attendance> existingAttendance = attendanceService.findAttendance(
                attendanceSession.getSessionId(),
                student.getUserId());

        if (existingAttendance.isPresent()) {
            model.addAttribute("error",
                    "You have already marked attendance for this session.");
            return "student/enter_session_code";
        }

        Attendance attendance = new Attendance();

        attendance.setAttendanceSession(attendanceSession);
        attendance.setStudent(student);
        attendance.setStatus(AttendanceStatus.Present);
        attendance.setMarkedTime(LocalDateTime.now());

        attendanceService.saveAttendance(attendance);

        model.addAttribute("success", "Attendance marked successfully.");
        model.addAttribute("attendanceSession", attendanceSession);

        return "student/enter_session_code";
    }

}