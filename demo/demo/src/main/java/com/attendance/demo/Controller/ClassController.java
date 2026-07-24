package com.attendance.demo.Controller;

import java.util.List;

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

import com.attendance.demo.entity.ClassEntity;
import com.attendance.demo.service.ClassService;

@RestController
@RequestMapping("/api/classes")
@CrossOrigin
public class ClassController {

    private final ClassService classService;

    public ClassController(ClassService classService) {

        this.classService = classService;

    }

    @GetMapping
    public List<ClassEntity> getAll() {

        return classService.getAllClasses();

    }

    @GetMapping("/search")
    public List<ClassEntity> search(

            @RequestParam(required = false) String name,

            @RequestParam(required = false) Integer year,

            @RequestParam(required = false) String stream

    ) {

        return classService.searchClass(
                name,
                year,
                stream);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassEntity> getById(
            @PathVariable Integer id) {

        ClassEntity cls = classService.getClassById(id);

        if (cls == null) {

            return ResponseEntity.notFound().build();

        }

        return ResponseEntity.ok(cls);

    }

    @PostMapping
    public ClassEntity add(
            @RequestBody ClassEntity classEntity) {

        return classService.saveClass(classEntity);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassEntity> update(

            @PathVariable Integer id,

            @RequestBody ClassEntity classEntity

    ) {

        ClassEntity updated = classService.updateClass(
                id,
                classEntity);

        if (updated == null) {

            return ResponseEntity.notFound().build();

        }

        return ResponseEntity.ok(updated);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(

            @PathVariable Integer id

    ) {

        classService.deleteClass(id);

        return ResponseEntity.ok(
                "Class deleted successfully");

    }

}
