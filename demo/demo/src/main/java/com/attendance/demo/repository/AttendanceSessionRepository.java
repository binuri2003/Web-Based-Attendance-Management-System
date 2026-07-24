package com.attendance.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.attendance.demo.entity.AttendanceSession;

@Repository
public interface AttendanceSessionRepository extends JpaRepository<AttendanceSession, Integer> {

    AttendanceSession findBySessionCode(String sessionCode);

    List<AttendanceSession> findBySubject_SubjectId(Integer subjectId);

    List<AttendanceSession> findBySubject_SubjectIdAndClassEntity_ClassId(
        Integer subjectId,
        Integer classId);
}