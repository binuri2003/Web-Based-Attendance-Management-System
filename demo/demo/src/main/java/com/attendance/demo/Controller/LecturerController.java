package com.attendance.demo.Controller;

import java.util.Random;
import java.util.List; 
import java.util.ArrayList; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.attendance.demo.entity.Attendance;
import com.attendance.demo.entity.AttendanceSession;
import com.attendance.demo.entity.AttendanceStatus;
import com.attendance.demo.entity.ClassEntity;
import com.attendance.demo.entity.Lecturer;
import com.attendance.demo.entity.Subject;
import com.attendance.demo.repository.ClassRepository;
import com.attendance.demo.repository.LecturerRepository;
import com.attendance.demo.repository.SubjectRepository;
import com.attendance.demo.service.AttendanceService;
import com.attendance.demo.service.AttendanceSessionService;
import com.attendance.demo.entity.User;
import com.attendance.demo.repository.AttendanceSessionRepository;

import jakarta.servlet.http.HttpSession;

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

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private AttendanceSessionRepository attendanceSessionRepository;

    @GetMapping("/dashboard")
    public String lecturerDashboard() {
        return "lecturer/dashboard";
    }

    @GetMapping("/create_session")
    public String createSessionPage(Model model, HttpSession httpSession) {

        User loggedUser = (User) httpSession.getAttribute("loggedUser");

        if (loggedUser == null) {
            return "redirect:/";
        }

        Lecturer lecturer = lecturerRepository
                .findByUsername(loggedUser.getUsername())
                .orElse(null);

        if (lecturer == null) {
            return "redirect:/";
        }

        // Show all subjects
        model.addAttribute(
                "subjectList",
                subjectRepository.findAll());

        // Show all classes
        model.addAttribute(
                "classList",
                classRepository.findAll());

        return "lecturer/create_session";
    }

    @PostMapping("/start-session")
    public String startSession(
            @RequestParam int subjectId,
            @RequestParam int classId,
            Model model,
            HttpSession httpSession) {

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
        User loggedUser = (User) httpSession.getAttribute("loggedUser");

        if (loggedUser == null) {
            return "redirect:/";
        }

        Lecturer lecturer = lecturerRepository
                .findByUsername(loggedUser.getUsername())
                .orElse(null);

        if (lecturer == null) {
            model.addAttribute("error", "Lecturer not found.");
            return "lecturer/create_session";
        }

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
    public String endSession(@RequestParam int sessionId) {

        AttendanceSession session = attendanceSessionService.getSessionById(sessionId);

        if (session != null) {
            session.setEndTime(new java.sql.Time(System.currentTimeMillis()));
            attendanceSessionService.saveSession(session);
        }

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
public String manageAttendance(

        @RequestParam(required = false) Integer subjectId,
        @RequestParam(required = false) Integer classId,
        @RequestParam(required = false) Integer sessionId,

        Model model) {

    // Load dropdowns
    model.addAttribute("subjectList", subjectRepository.findAll());
    model.addAttribute("classList", classRepository.findAll());

    // Load sessions
    if (subjectId != null && classId != null) {
        model.addAttribute(
                "sessionList",
                attendanceSessionRepository
                        .findBySubject_SubjectIdAndClassEntity_ClassId(
                                subjectId,
                                classId));
    } else {
        model.addAttribute("sessionList", java.util.Collections.emptyList());
    }

    // Remember selected values
    model.addAttribute("selectedSubject", subjectId);
    model.addAttribute("selectedClass", classId);
    model.addAttribute("selectedSession", sessionId);

    // Load attendance
    if (sessionId != null) {

        List<Attendance> attendanceList =
                attendanceService.getAttendanceBySession(sessionId);

        model.addAttribute("attendanceList", attendanceList);

        long present = attendanceList.stream()
                .filter(a -> a.getStatus() == AttendanceStatus.Present)
                .count();

        long absent = attendanceList.stream()
                .filter(a -> a.getStatus() == AttendanceStatus.Absent)
                .count();

        long pending = attendanceList.stream()
                .filter(a -> a.getStatus() == AttendanceStatus.Pending)
                .count();

        model.addAttribute("presentCount", present);
        model.addAttribute("absentCount", absent);
        model.addAttribute("pendingCount", pending);

    } else {

        model.addAttribute("attendanceList", java.util.Collections.emptyList());

        model.addAttribute("presentCount", 0);
        model.addAttribute("absentCount", 0);
        model.addAttribute("pendingCount", 0);
    }

    return "lecturer/manage_attendance";
}

    @GetMapping("/view_attendance")
    public String viewAttendance(Model model) {

        model.addAttribute(
                "sessionList",
                attendanceSessionService.getAllSessions());

        return "lecturer/view_attendance";
    }

    @GetMapping("/student_history")
    public String studentHistory() {
        return "lecturer/student_history";
    }

    @GetMapping("/session/{id}")
    public String viewSessionAttendance(@PathVariable Integer id, Model model) {

        model.addAttribute("attendanceList",
                attendanceService.getAttendanceBySession(id));

        return "lecturer/attendance_list";
    }

    @GetMapping("/edit-attendance/{id}")
    public String editAttendance(@PathVariable Integer id, Model model) {

        model.addAttribute("attendance",
                attendanceService.getAttendanceById(id));

        return "lecturer/edit_attendance";
    }

    @PostMapping("/update-attendance")
    public String updateAttendance(
            @RequestParam Integer attendanceId,
            @RequestParam AttendanceStatus status) {

        attendanceService.updateAttendance(attendanceId, status);

        return "redirect:/lecturer/view_attendance";
    }

    @PostMapping("/save-attendance")
public String saveAttendance(

        @RequestParam Integer sessionId,

        @RequestParam("attendanceIds") List<Integer> attendanceIds,

        @RequestParam("statuses") List<AttendanceStatus> statuses) {

    for (int i = 0; i < attendanceIds.size(); i++) {

        attendanceService.updateAttendance(
                attendanceIds.get(i),
                statuses.get(i));

    }

    AttendanceSession session =
            attendanceSessionService.getSessionById(sessionId);

    return "redirect:/lecturer/manage_attendance?subjectId="
            + session.getSubject().getSubjectId()
            + "&classId="
            + session.getClassEntity().getClassId()
            + "&sessionId="
            + sessionId;
}

}