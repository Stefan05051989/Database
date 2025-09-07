package nl.codecafe.crud.dto.course;

import nl.codecafe.crud.dto.student.StudentSummaryDTO;
import nl.codecafe.crud.models.Course;

import java.util.List;

public record CourseDTO(
        Long id,
        String name,
        List<StudentSummaryDTO> students
) {
    public static CourseDTO fromEntity(Course course) {
        List<StudentSummaryDTO> studentDtos = course.getStudents()
                .stream()
                .map(StudentSummaryDTO::fromEntity)
                .toList();

        return new CourseDTO(
                course.getId(),
                course.getName(),
                studentDtos
        );
    }
}
