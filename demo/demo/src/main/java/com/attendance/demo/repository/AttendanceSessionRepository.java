package com.attendance.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.attendance.demo.entity.AttendanceSession;

@Repository
public interface AttendanceSessionRepository extends JpaRepository<AttendanceSession, Integer> {

    List<AttendanceSession> findBySubject_SubjectId(Integer subjectId);

}