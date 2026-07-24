package com.attendance.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.attendance.demo.entity.ClassEntity;
import com.attendance.demo.repository.ClassRepository;

@Service
public class ClassService {

        private final ClassRepository classRepository;

        public ClassService(ClassRepository classRepository) {

                this.classRepository = classRepository;

        }

        public List<ClassEntity> getAllClasses() {

                return classRepository.findAll();

        }

        public ClassEntity getClassById(Integer id) {

                return classRepository.findById(id)
                                .orElse(null);

        }

        // Search class

        public List<ClassEntity> searchClass(
                        String name,
                        Integer year,
                        String stream) {

                if (name != null && !name.isEmpty()
                                && year != null
                                && stream != null
                                && !stream.isEmpty()) {

                        return classRepository
                                        .findByClassNameContainingIgnoreCaseAndYearAndStreamIgnoreCase(
                                                        name,
                                                        year,
                                                        stream);

                }

                if (name != null && !name.isEmpty()) {

                        return classRepository
                                        .findByClassNameContainingIgnoreCase(name);

                }

                if (year != null) {

                        return classRepository.findByYear(year);

                }

                if (stream != null && !stream.isEmpty()) {

                        return classRepository.findByStreamIgnoreCase(stream);

                }

                return classRepository.findAll();

        }

        public ClassEntity saveClass(ClassEntity classEntity) {

                return classRepository.save(classEntity);

        }

        public ClassEntity updateClass(
                        Integer id,
                        ClassEntity updatedClass) {

                ClassEntity existing = classRepository.findById(id)
                                .orElse(null);

                if (existing != null) {

                        existing.setClassName(
                                        updatedClass.getClassName());

                        existing.setProgram(
                                        updatedClass.getProgram());

                        existing.setYear(
                                        updatedClass.getYear());

                        existing.setStream(
                                        updatedClass.getStream());

                        existing.setSemester(
                                        updatedClass.getSemester());

                        existing.setAcademicYear(
                                        updatedClass.getAcademicYear());

                        return classRepository.save(existing);

                }

                return null;

        }

        @Transactional
        public void deleteClass(Integer id) {

                ClassEntity existing = classRepository.findById(id)
                                .orElse(null);

                if (existing == null) {

                        throw new RuntimeException("Class not found");

                }

                classRepository.delete(existing);

        }

}