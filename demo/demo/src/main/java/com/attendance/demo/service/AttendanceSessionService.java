package com.attendance.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.attendance.demo.entity.AttendanceSession;
import com.attendance.demo.repository.AttendanceSessionRepository;

@Service
public class AttendanceSessionService {

    @Autowired
    private AttendanceSessionRepository attendanceSessionRepository;

    public AttendanceSession saveSession(AttendanceSession session) {
        return attendanceSessionRepository.save(session);
    }

    public List<AttendanceSession> getAllSessions() {
        return attendanceSessionRepository.findAll();
    }

    public AttendanceSession getSessionById(Integer sessionId) {
        return attendanceSessionRepository.findById(sessionId).orElse(null);
    }

    public void deleteSession(Integer sessionId) {
        attendanceSessionRepository.deleteById(sessionId);
    }
}