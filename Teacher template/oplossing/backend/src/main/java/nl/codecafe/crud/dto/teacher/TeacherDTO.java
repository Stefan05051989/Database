package nl.codecafe.crud.dto.teacher;

import nl.codecafe.crud.dto.course.CourseSummaryDTO;
import nl.codecafe.crud.models.Teacher;

import java.util.List;

public record TeacherDTO (
    Long id,
    String name,
    String email,
    List<CourseSummaryDTO> courses
) {
    public static TeacherDTO fromEntity(Teacher teacher) {
        List<CourseSummaryDTO> courseDtos = teacher.getCourses()
                .stream()
                .map(CourseSummaryDTO::fromEntity)
                .toList();

        return new TeacherDTO(
            teacher.getId(), 
            teacher.getName(), 
            teacher.getEmail(), 
            courseDtos
        );
    }
}
