package com.attendance.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.attendance.demo.entity.Subject;


public interface SubjectRepository extends JpaRepository<Subject,Integer>{

}