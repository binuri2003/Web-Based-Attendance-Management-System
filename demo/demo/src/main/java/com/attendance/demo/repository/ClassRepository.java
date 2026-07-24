package com.attendance.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.attendance.demo.entity.ClassEntity;

@Repository
public interface ClassRepository extends JpaRepository<ClassEntity, Integer> {

    List<ClassEntity> findByClassNameContainingIgnoreCase(String className);

    List<ClassEntity> findByYear(Integer year);

    List<ClassEntity> findByStreamIgnoreCase(String stream);

    List<ClassEntity> findByClassNameContainingIgnoreCaseAndYearAndStreamIgnoreCase(
            String className,
            Integer year,
            String stream);

}