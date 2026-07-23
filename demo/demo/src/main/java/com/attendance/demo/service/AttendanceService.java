package com.attendance.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.attendance.demo.entity.Attendance;
import com.attendance.demo.repository.AttendanceRepository;

import java.util.Optional;

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
}