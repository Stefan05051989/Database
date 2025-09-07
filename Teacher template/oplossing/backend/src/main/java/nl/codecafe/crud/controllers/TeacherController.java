package nl.codecafe.crud.controllers;

import jakarta.validation.Valid;
import nl.codecafe.crud.dto.teacher.TeacherCreateDTO;
import nl.codecafe.crud.dto.teacher.TeacherDTO;
import nl.codecafe.crud.dto.teacher.TeacherUpdateDTO;
import nl.codecafe.crud.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping
    public ResponseEntity<TeacherDTO> createTeacher(@Valid @RequestBody TeacherCreateDTO createDto) {
        TeacherDTO created = teacherService.createTeacher(createDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherDTO> updateTeacher(
            @PathVariable Long id,
            @Valid @RequestBody TeacherUpdateDTO updateDto) {
        TeacherDTO updated = teacherService.updateTeacher(id, updateDto);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDTO> getTeacher(@PathVariable Long id) {
        TeacherDTO teacher = teacherService.findById(id);
        return ResponseEntity.ok(teacher);
    }

    @GetMapping
    public ResponseEntity<List<TeacherDTO>> getAllTeachers() {
        List<TeacherDTO> teachers = teacherService.findAll();
        return ResponseEntity.ok(teachers);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.noContent().build();
    }
}
