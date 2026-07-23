package com.attendance.demo.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.attendance.demo.entity.Lecturer;


@Repository
public interface LecturerRepository extends JpaRepository<Lecturer, Integer> {


    Optional<Lecturer> findByUsername(String username);


    boolean existsByEmail(String email);



    List<Lecturer> findByLecturerNameContainingIgnoreCase(
            String lecturerName
    );



    List<Lecturer> findByUsernameContainingIgnoreCase(
            String username
    );



    List<Lecturer> findByLecturerNameContainingIgnoreCaseOrUsernameContainingIgnoreCase(
            String lecturerName,
            String username
    );


}