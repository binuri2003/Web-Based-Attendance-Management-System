package com.attendance.demo.Controller;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

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
import com.attendance.demo.repository.StudentSubjectRepository;
import com.attendance.demo.service.AttendanceService;
import com.attendance.demo.service.AttendanceSessionService;
import com.attendance.demo.dto.AttendancePercentageDTO;
import com.attendance.demo.entity.StudentSubject;

import jakarta.servlet.http.HttpSession;

@Controller
public class StudentController {

    @Autowired
    private AttendanceSessionService attendanceSessionService;

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentSubjectRepository studentSubjectRepository;

    @GetMapping("/student/dashboard")
    public String studentDashboard() {
        return "student/dashboard";
    }

    @GetMapping("/student/enter_session_code")
    public String enterSessionCode() {
        return "student/enter_session_code";
    }

    @GetMapping("/student/attendance_history")
    public String attendanceHistory(
            @RequestParam(required = false) Integer subjectId,
            HttpSession session,
            Model model) {

        User loggedUser = (User) session.getAttribute("loggedUser");

        if (loggedUser == null) {
            return "redirect:/";
        }

        List<StudentSubject> subjectList = studentSubjectRepository.findByStudent_UserId(loggedUser.getUserId());

        model.addAttribute("subjectList", subjectList);

        List<Attendance> attendanceList;

        if (subjectId == null) {
            attendanceList = attendanceService.getAttendanceByStudent(loggedUser.getUserId());
        } else {
            attendanceList = attendanceService.getAttendanceByStudentAndSubject(
                    loggedUser.getUserId(), subjectId);
        }

        model.addAttribute("attendanceList", attendanceList);

        int total = attendanceList.size();

        long present = attendanceList.stream()
                .filter(a -> a.getStatus().name().equals("Present"))
                .count();

        long absent = total - present;

        double percentage = total == 0 ? 0 : (present * 100.0) / total;

        model.addAttribute("totalSessions", total);
        model.addAttribute("presentSessions", present);
        model.addAttribute("absentSessions", absent);
        model.addAttribute("attendancePercentage", percentage);

        model.addAttribute("selectedSubject", subjectId);

        return "student/attendance_history";
    }

    @GetMapping("/student/attendance_percentage")
    public String attendancePercentage(HttpSession session, Model model) {

        User loggedUser = (User) session.getAttribute("loggedUser");

        if (loggedUser == null) {
            return "redirect:/";
        }

        List<AttendancePercentageDTO> percentageList = attendanceService
                .getAttendancePercentage(loggedUser.getUserId());

        model.addAttribute("percentageList", percentageList);

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

    @GetMapping("/student/subjects")
    public String viewSubjects(HttpSession session, Model model) {

        User loggedUser = (User) session.getAttribute("loggedUser");

        if (loggedUser == null) {
            return "redirect:/";
        }

        List<StudentSubject> subjectList = studentSubjectRepository.findByStudent_UserId(
                loggedUser.getUserId());

        model.addAttribute("subjectList", subjectList);

        return "student/subjects";
    }

}