package nl.codecafe.crud.controllers;

import nl.codecafe.crud.dto.teacher.TeacherDTO;
import nl.codecafe.crud.services.TeacherCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teacher-courses")
public class TeacherCourseController {
    private final TeacherCourseService teacherCourseService;

    @Autowired
    public TeacherCourseController(TeacherCourseService teacherCourseService) {
        this.teacherCourseService = teacherCourseService;
    }

    @PostMapping("/assign/{teacherId}/to/{courseId}")
    public ResponseEntity<TeacherDTO> assignTeacherToCourse(
            @PathVariable Long teacherId,
            @PathVariable Long courseId) {
        TeacherDTO course = teacherCourseService.assignTeacherToCourse(teacherId, courseId);
        return ResponseEntity.ok(course);
    }

    @DeleteMapping("/remove-from/{courseId}")
    public ResponseEntity<TeacherDTO> removeTeacherFromCourse(@PathVariable Long courseId) {
        TeacherDTO course = teacherCourseService.removeTeacherFromCourse(courseId);
        return ResponseEntity.ok(course);
    }

    @GetMapping("/teacher-for/{courseId}")
    public ResponseEntity<TeacherDTO> getTeacherForCourse(@PathVariable Long courseId) {
        TeacherDTO teacher = teacherCourseService.getTeacherForCourse(courseId);
        return ResponseEntity.ok(teacher);
    }
}
