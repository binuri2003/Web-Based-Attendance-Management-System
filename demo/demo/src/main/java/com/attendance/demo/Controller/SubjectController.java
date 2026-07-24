package com.attendance.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.attendance.demo.dto.SubjectRequest;
import com.attendance.demo.entity.Lecturer;
import com.attendance.demo.entity.Subject;
import com.attendance.demo.repository.LecturerRepository;
import com.attendance.demo.repository.SubjectRepository;

@RestController
@RequestMapping("/api/subjects")
@CrossOrigin
public class SubjectController {

        @Autowired
        private SubjectRepository subjectRepository;

        @Autowired
        private LecturerRepository lecturerRepository;

        @GetMapping
        public List<Subject> getSubjects() {

                return subjectRepository.findAll();

        }

        @GetMapping("/{id}")
        public Subject getSubject(@PathVariable int id) {

                return subjectRepository.findById(id).get();

        }

        @PostMapping
        public Subject addSubject(
                        @RequestBody SubjectRequest request) {

                Lecturer lecturer = lecturerRepository
                                .findById(request.getLecturerId())
                                .orElseThrow();

                Subject subject = new Subject();

                subject.setSubjectCode(
                                request.getSubjectCode());

                subject.setSubjectName(
                                request.getSubjectName());

                subject.setCredits(
                                request.getCredits());

                subject.setLecturer(lecturer);

                return subjectRepository.save(subject);

        }

        @PutMapping("/{id}")
        public Subject updateSubject(
                        @PathVariable int id,
                        @RequestBody SubjectRequest request) {

                Subject subject = subjectRepository.findById(id).get();

                Lecturer lecturer = lecturerRepository
                                .findById(request.getLecturerId())
                                .orElseThrow();

                subject.setSubjectCode(
                                request.getSubjectCode());

                subject.setSubjectName(
                                request.getSubjectName());

                subject.setCredits(
                                request.getCredits());

                subject.setLecturer(lecturer);

                return subjectRepository.save(subject);

        }

        @DeleteMapping("/{id}")
        public void deleteSubject(
                        @PathVariable int id) {

                subjectRepository.deleteById(id);

        }

}
