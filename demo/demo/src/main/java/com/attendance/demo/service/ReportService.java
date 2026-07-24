package com.attendance.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.attendance.demo.dto.StudentAttendanceReportResponse;
import com.attendance.demo.dto.SubjectAttendanceReportResponse;
import com.attendance.demo.dto.SubjectSessionReportResponse;
import com.attendance.demo.entity.Attendance;
import com.attendance.demo.entity.AttendanceSession;
import com.attendance.demo.entity.Student;
import com.attendance.demo.entity.StudentSubject;
import com.attendance.demo.entity.Subject;
import com.attendance.demo.repository.AttendanceRepository;
import com.attendance.demo.repository.AttendanceSessionRepository;
import com.attendance.demo.repository.StudentRepository;
import com.attendance.demo.repository.StudentSubjectRepository;
import com.attendance.demo.repository.SubjectRepository;

@Service
public class ReportService {

    private final AttendanceRepository attendanceRepository;
    private final AttendanceSessionRepository sessionRepository;
    private final StudentRepository studentRepository;
    private final StudentSubjectRepository studentSubjectRepository;
    private final SubjectRepository subjectRepository;

    public ReportService(
            AttendanceRepository attendanceRepository,
            AttendanceSessionRepository sessionRepository,
            StudentRepository studentRepository,
            StudentSubjectRepository studentSubjectRepository,
            SubjectRepository subjectRepository) {

        this.attendanceRepository = attendanceRepository;
        this.sessionRepository = sessionRepository;
        this.studentRepository = studentRepository;
        this.studentSubjectRepository = studentSubjectRepository;
        this.subjectRepository = subjectRepository;
    }

    // Student Report
    public List<StudentAttendanceReportResponse> getStudentAttendanceReport(
            String keyword,
            Integer subjectId) {

        List<StudentAttendanceReportResponse> result = new ArrayList<>();

        List<Student> students = studentRepository
                .findByRegistrationNoContainingIgnoreCaseOrStudentNameContainingIgnoreCaseOrUsernameContainingIgnoreCase(
                        keyword,
                        keyword,
                        keyword);

        Subject subject = subjectRepository.findById(subjectId).orElse(null);

        for (Student student : students) {

            List<Attendance> attendanceList =
                    attendanceRepository.findByStudentAndAttendanceSession_Subject_SubjectId(
                            student,
                            subjectId);

            int present = 0;
            int absent = 0;

            for (Attendance attendance : attendanceList) {

                if (attendance.getStatus().toString().equals("Present")) {
                    present++;
                } else {
                    absent++;
                }
            }

            int total = present + absent;

            double percentage = total == 0
                    ? 0
                    : (present * 100.0) / total;

            result.add(
                    new StudentAttendanceReportResponse(
                            student.getRegistrationNo(),
                            student.getStudentName(),
                            subject == null
                                    ? ""
                                    : subject.getSubjectCode() + " - " + subject.getSubjectName(),
                            present,
                            absent,
                            total,
                            percentage));
        }

        return result;
    }

    // Subject Report
    public List<SubjectAttendanceReportResponse> getSubjectAttendanceReport(
            Integer subjectId) {

        List<SubjectAttendanceReportResponse> result = new ArrayList<>();

        List<StudentSubject> students =
                studentSubjectRepository.findBySubject_SubjectId(subjectId);

        for (StudentSubject studentSubject : students) {

            Student student = studentSubject.getStudent();

            List<Attendance> attendanceList =
                    attendanceRepository.findByStudentAndAttendanceSession_Subject_SubjectId(
                            student,
                            subjectId);

            int present = 0;
            int absent = 0;

            for (Attendance attendance : attendanceList) {

                if (attendance.getStatus().toString().equals("Present")) {
                    present++;
                } else {
                    absent++;
                }
            }

            int total = present + absent;

            double percentage = total == 0
                    ? 0
                    : (present * 100.0) / total;

            result.add(
                    new SubjectAttendanceReportResponse(
                            student.getStudentName(),
                            student.getRegistrationNo(),
                            present,
                            absent,
                            percentage));
        }

        return result;
    }

    // Session Report
    public List<SubjectSessionReportResponse> getSubjectSessionReport() {

        List<SubjectSessionReportResponse> result = new ArrayList<>();

        List<Subject> subjects = subjectRepository.findAll();

        for (Subject subject : subjects) {

            List<AttendanceSession> sessions =
                    sessionRepository.findBySubject_SubjectId(
                            subject.getSubjectId());

            String lecturer = "Not Assigned";

            if (subject.getLecturer() != null) {
                lecturer = subject.getLecturer().getLecturerName();
            }

            result.add(
                    new SubjectSessionReportResponse(
                            subject.getSubjectCode(),
                            subject.getSubjectName(),
                            lecturer,
                            sessions.size()));
        }

        return result;
    }
}