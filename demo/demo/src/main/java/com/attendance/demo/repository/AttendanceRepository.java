package com.attendance.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.attendance.demo.entity.Attendance;
import com.attendance.demo.entity.AttendanceSession;
import com.attendance.demo.entity.Student;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

    // Your methods
    Optional<Attendance> findByAttendanceSessionSessionIdAndStudentUserId(
            Integer sessionId,
            Integer userId);

    List<Attendance> findByStudentUserId(Integer userId);

    List<Attendance> findByAttendanceSessionSessionId(Integer sessionId);

    // Partner's methods (updated to match attendanceSession field)
    List<Attendance> findByStudent(Student student);

    List<Attendance> findByAttendanceSession(AttendanceSession attendanceSession);

    List<Attendance> findByStudentAndAttendanceSession_Subject_SubjectId(
            Student student,
            Integer subjectId);

    List<Attendance> findByAttendanceSession_Subject_SubjectId(
            Integer subjectId);
}