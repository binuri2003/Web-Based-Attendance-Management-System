package com.attendance.demo.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.attendance.demo.dto.StudentAttendanceReportResponse;
import com.attendance.demo.dto.SubjectAttendanceReportResponse;
import com.attendance.demo.dto.SubjectSessionReportResponse;
import com.attendance.demo.service.ReportService;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {

        this.reportService = reportService;

    }

    @GetMapping("/student")
    public List<StudentAttendanceReportResponse> getStudentReport(
            @RequestParam String keyword,
            @RequestParam Integer subjectId) {

        return reportService
                .getStudentAttendanceReport(keyword, subjectId);

    }

    @GetMapping("/subject/{subjectId}")
    public List<SubjectAttendanceReportResponse> getSubjectReport(
            @PathVariable Integer subjectId) {

        return reportService
                .getSubjectAttendanceReport(subjectId);

    }

    @GetMapping("/sessions")
    public List<SubjectSessionReportResponse> getSessionReport() {

        return reportService
                .getSubjectSessionReport();

    }

}