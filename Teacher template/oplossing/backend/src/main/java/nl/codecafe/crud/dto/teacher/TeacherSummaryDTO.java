package nl.codecafe.crud.dto.teacher;

import nl.codecafe.crud.models.Teacher;

public record TeacherSummaryDTO (
    Long id,
    String name,
    String email
) {
    public static TeacherSummaryDTO fromEntity(Teacher teacher) {
        return new TeacherSummaryDTO(
            teacher.getId(), 
            teacher.getName(), 
            teacher.getEmail()
        );
    }
}
