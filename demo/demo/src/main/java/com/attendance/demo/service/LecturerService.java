package com.attendance.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.attendance.demo.dto.LecturerRequest;
import com.attendance.demo.dto.LecturerResponse;
import com.attendance.demo.entity.Lecturer;
import com.attendance.demo.entity.Subject;
import com.attendance.demo.repository.LecturerRepository;
import com.attendance.demo.repository.SubjectRepository;
import com.attendance.demo.repository.UserRepository;

@Service
public class LecturerService {

        @Autowired
        private LecturerRepository lecturerRepository;

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private SubjectRepository subjectRepository;

        public List<LecturerResponse> getAllLecturers() {

                List<Lecturer> lecturers = lecturerRepository.findAll();

                List<LecturerResponse> response = new ArrayList<>();

                for (Lecturer lecturer : lecturers) {

                        response.add(
                                        convertToResponse(lecturer));

                }

                return response;

        }

        private LecturerResponse convertToResponse(
                        Lecturer lecturer) {

                LecturerResponse response = new LecturerResponse();

                response.setLecturerId(
                                lecturer.getUserId());

                response.setUsername(
                                lecturer.getUsername());

                response.setLecturerName(
                                lecturer.getLecturerName());

                response.setEmail(
                                lecturer.getEmail());

                List<Subject> subjects = subjectRepository.findByLecturer(lecturer);

                if (subjects.isEmpty()) {

                        response.setSubjects(
                                        "No Subjects Assigned");

                } else {

                        String subjectNames = subjects.stream()
                                        .map(subject -> subject.getSubjectName())
                                        .collect(Collectors.joining(", "));

                        response.setSubjects(subjectNames);

                }

                return response;

        }

        public Lecturer addLecturer(
                        LecturerRequest request) {

                if (userRepository.existsByUsername(
                                request.getUsername())) {

                        throw new RuntimeException(
                                        "Username already exists");

                }

                if (lecturerRepository.existsByEmail(
                                request.getEmail())) {

                        throw new RuntimeException(
                                        "Email already exists");

                }

                Lecturer lecturer = new Lecturer();

                lecturer.setUsername(
                                request.getUsername());

                lecturer.setPassword(
                                request.getPassword());

                lecturer.setLecturerName(
                                request.getLecturerName());

                lecturer.setEmail(
                                request.getEmail());

                return lecturerRepository.save(lecturer);

        }

        public Lecturer updateLecturer(
                        Integer id,
                        LecturerRequest request) {

                Lecturer lecturer = lecturerRepository
                                .findById(id)
                                .orElseThrow(
                                                () -> new RuntimeException(
                                                                "Lecturer not found"));

                lecturer.setUsername(
                                request.getUsername());

                lecturer.setLecturerName(
                                request.getLecturerName());

                lecturer.setEmail(
                                request.getEmail());

                return lecturerRepository.save(lecturer);

        }

        public void deleteLecturer(
                        Integer id) {

                Lecturer lecturer = lecturerRepository
                                .findById(id)
                                .orElseThrow(
                                                () -> new RuntimeException(
                                                                "Lecturer not found"));

                List<Subject> subjects = subjectRepository.findByLecturer(
                                lecturer);

                for (Subject subject : subjects) {

                        subject.setLecturer(null);

                }

                subjectRepository.saveAll(subjects);

                lecturerRepository.delete(lecturer);

        }

        public List<LecturerResponse> searchLecturer(
                        String keyword) {

                List<Lecturer> lecturers = lecturerRepository
                                .findByLecturerNameContainingIgnoreCaseOrUsernameContainingIgnoreCase(
                                                keyword,
                                                keyword);

                List<LecturerResponse> response = new ArrayList<>();

                for (Lecturer lecturer : lecturers) {

                        response.add(
                                        convertToResponse(lecturer));

                }

                return response;

        }

        public List<LecturerResponse> searchBySubject(
                        String subjectName) {

                List<Subject> subjects = subjectRepository
                                .findBySubjectNameContainingIgnoreCase(
                                                subjectName);

                List<LecturerResponse> response = new ArrayList<>();

                for (Subject subject : subjects) {

                        Lecturer lecturer = subject.getLecturer();

                        if (lecturer != null) {

                                response.add(
                                                convertToResponse(lecturer));

                        }

                }

                return response;

        }

}