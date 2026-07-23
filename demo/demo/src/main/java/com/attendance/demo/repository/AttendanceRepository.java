package com.attendance.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.attendance.demo.entity.Attendance;
import com.attendance.demo.entity.AttendanceSession;
import com.attendance.demo.entity.Student;



@Repository
public interface AttendanceRepository 
extends JpaRepository<Attendance,Integer>{



    List<Attendance> findByStudent(Student student);



    List<Attendance> findBySession(AttendanceSession session);



    List<Attendance> findByStudentAndSession_Subject_SubjectId(
            Student student,
            Integer subjectId
    );



    List<Attendance> findBySession_Subject_SubjectId(
            Integer subjectId
    );


}