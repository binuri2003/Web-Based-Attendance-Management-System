package com.attendance.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.attendance.demo.dto.LecturerRequest;
import com.attendance.demo.dto.LecturerResponse;
import com.attendance.demo.entity.Lecturer;
import com.attendance.demo.service.LecturerService;

@RestController
@RequestMapping("/api/lecturers")
@CrossOrigin
public class LecturersController {

    @Autowired
    private LecturerService lecturerService;

    @GetMapping
    public List<LecturerResponse> getAllLecturers() {

        return lecturerService.getAllLecturers();

    }

    @PostMapping
    public ResponseEntity<?> addLecturer(
            @RequestBody LecturerRequest request) {

        try {

            Lecturer lecturer = lecturerService.addLecturer(request);

            return ResponseEntity.ok(lecturer);

        } catch (Exception e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());

        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateLecturer(
            @PathVariable Integer id,
            @RequestBody LecturerRequest request) {

        try {

            Lecturer lecturer = lecturerService.updateLecturer(id, request);

            return ResponseEntity.ok(lecturer);

        } catch (Exception e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());

        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLecturer(
            @PathVariable Integer id) {

        try {

            lecturerService.deleteLecturer(id);

            return ResponseEntity.ok(
                    "Lecturer deleted successfully");

        } catch (Exception e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());

        }

    }

    @GetMapping("/search")
    public List<LecturerResponse> searchLecturer(
            @RequestParam String keyword) {

        return lecturerService.searchLecturer(keyword);

    }

    @GetMapping("/search/subject")
    public List<LecturerResponse> searchBySubject(
            @RequestParam String subject) {

        return lecturerService.searchBySubject(subject);

    }

}