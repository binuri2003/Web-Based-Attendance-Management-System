package com.attendance.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.attendance.demo.dto.AttendancePercentageDTO;
import com.attendance.demo.entity.Attendance;
import com.attendance.demo.entity.AttendanceStatus;
import com.attendance.demo.repository.AttendanceRepository;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    public Attendance saveAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    public List<Attendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }

    public Attendance getAttendanceById(Integer attendanceId) {
        return attendanceRepository.findById(attendanceId).orElse(null);
    }

    public void deleteAttendance(Integer attendanceId) {
        attendanceRepository.deleteById(attendanceId);
    }

    public Optional<Attendance> findAttendance(Integer sessionId, Integer userId) {
        return attendanceRepository.findByAttendanceSessionSessionIdAndStudentUserId(
                sessionId, userId);
    }

    public List<Attendance> getAttendanceByStudent(Integer userId) {
        return attendanceRepository.findByStudentUserId(userId);
    }

    public List<Attendance> getAttendanceByStudentAndSubject(Integer userId, Integer subjectId) {
    return attendanceRepository.findByStudentUserIdAndAttendanceSessionSubjectSubjectId(
            userId, subjectId);
    }

    public List<AttendancePercentageDTO> getAttendancePercentage(Integer userId) {

        List<Attendance> attendanceList = attendanceRepository.findByStudentUserId(userId);

        Map<String, int[]> subjectData = new HashMap<>();

        for (Attendance attendance : attendanceList) {

            String subjectName = attendance.getAttendanceSession()
                    .getSubject()
                    .getSubjectName();

            subjectData.putIfAbsent(subjectName, new int[] { 0, 0 });

            int[] counts = subjectData.get(subjectName);

            // Total sessions
            counts[1]++;

            // Present sessions
            if (attendance.getStatus() == AttendanceStatus.Present) {
                counts[0]++;
            }
        }

        List<AttendancePercentageDTO> result = new ArrayList<>();

        for (Map.Entry<String, int[]> entry : subjectData.entrySet()) {

            String subject = entry.getKey();
            int present = entry.getValue()[0];
            int total = entry.getValue()[1];

            double percentage = total == 0 ? 0 : (present * 100.0) / total;

            result.add(new AttendancePercentageDTO(
                    subject,
                    present,
                    total,
                    percentage));
        }

        return result;
    }

    public List<Attendance> getAttendanceBySession(Integer sessionId) {
        return attendanceRepository.findByAttendanceSessionSessionId(sessionId);
    }

    public void updateAttendance(Integer attendanceId, AttendanceStatus status) {

        Attendance attendance = attendanceRepository
                .findById(attendanceId)
                .orElse(null);

        if (attendance != null) {

            attendance.setStatus(status);

            attendanceRepository.save(attendance);

        }
    }
}