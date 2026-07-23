package com.attendance.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.attendance.demo.entity.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

}