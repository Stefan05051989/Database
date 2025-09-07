package nl.codecafe.crud.services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import nl.codecafe.crud.dto.course.CourseDTO;
import nl.codecafe.crud.dto.teacher.TeacherDTO;
import nl.codecafe.crud.models.Course;
import nl.codecafe.crud.models.Teacher;
import nl.codecafe.crud.repositories.CourseRepository;
import nl.codecafe.crud.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherCourseService {
    final private TeacherRepository teacherRepository;
    final private CourseRepository courseRepository;

    @Autowired
    public TeacherCourseService(TeacherRepository teacherRepository, CourseRepository courseRepository) {
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
    }

    @Transactional
    public TeacherDTO assignTeacherToCourse(Long teacherId, Long courseId) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new EntityNotFoundException("Teacher not found with ID: " + teacherId));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with ID: " + courseId));

        if (course.getTeacher() != null) {
            throw new IllegalStateException("Course already has a teacher assigned");
        }

        teacher.getCourses().add(course);
        course.setTeacher(teacher);

        Teacher savedTeacher = teacherRepository.save(teacher);

        return TeacherDTO.fromEntity(savedTeacher); 
    }

    @Transactional
    public TeacherDTO removeTeacherFromCourse(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with ID: " + courseId));

        Teacher teacher = course.getTeacher();

        if (teacher == null) {
            throw new IllegalStateException("Course has no teacher assigned");
        }

        teacher.getCourses().remove(course);
        course.setTeacher(null);

        Teacher savedTeacher = teacherRepository.save(teacher);

        return TeacherDTO.fromEntity(savedTeacher);
    }

    public TeacherDTO getTeacherForCourse(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with ID: " + courseId));

        if (course.getTeacher() == null) {
            throw new IllegalStateException("Course has no teacher assigned");
        }

        return TeacherDTO.fromEntity(course.getTeacher());
    }
}
