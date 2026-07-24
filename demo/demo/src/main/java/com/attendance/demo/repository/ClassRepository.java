package com.attendance.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.attendance.demo.entity.ClassEntity;


@Repository
public interface ClassRepository extends JpaRepository<ClassEntity, Integer> {


    // Search class name
    List<ClassEntity> findByClassNameContainingIgnoreCase(String className);


    // Filter by year
    List<ClassEntity> findByYear(Integer year);


    // Filter by stream
    List<ClassEntity> findByStreamIgnoreCase(String stream);


    // Search with all filters
    List<ClassEntity> findByClassNameContainingIgnoreCaseAndYearAndStreamIgnoreCase(
            String className,
            Integer year,
            String stream
    );

}