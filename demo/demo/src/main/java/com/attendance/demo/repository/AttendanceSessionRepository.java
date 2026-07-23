package com.attendance.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.attendance.demo.entity.AttendanceSession;

public interface AttendanceSessionRepository extends JpaRepository<AttendanceSession, Integer> {

    AttendanceSession findBySessionCode(String sessionCode);

}