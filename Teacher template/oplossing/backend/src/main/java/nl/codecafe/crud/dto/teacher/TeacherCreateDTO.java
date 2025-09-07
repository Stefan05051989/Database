package nl.codecafe.crud.dto.teacher;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import nl.codecafe.crud.models.Teacher;

public record TeacherCreateDTO (
    @NotBlank(message = "Name is required")
    String name,

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be a valid format")
    String email
) {
    public Teacher toEntity() {
        Teacher teacher = new Teacher();
        teacher.setName(this.name);
        teacher.setEmail(this.email);
        return teacher;
    }
}
