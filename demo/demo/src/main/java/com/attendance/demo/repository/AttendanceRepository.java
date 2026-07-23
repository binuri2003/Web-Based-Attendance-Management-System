package com.attendance.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.attendance.demo.entity.Attendance;

import java.util.Optional;
import java.util.List;


public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {
    Optional<Attendance> findByAttendanceSessionSessionIdAndStudentUserId(
            Integer sessionId,
            Integer userId);

    List<Attendance> findByStudentUserId(Integer userId);

}