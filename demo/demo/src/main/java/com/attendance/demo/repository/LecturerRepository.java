package com.attendance.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.attendance.demo.entity.Lecturer;

public interface LecturerRepository extends JpaRepository<Lecturer, Integer> {

}
