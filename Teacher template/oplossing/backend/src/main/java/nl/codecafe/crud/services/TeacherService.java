package nl.codecafe.crud.services;

import jakarta.persistence.EntityNotFoundException;
import nl.codecafe.crud.dto.teacher.TeacherCreateDTO;
import nl.codecafe.crud.dto.teacher.TeacherDTO;
import nl.codecafe.crud.dto.teacher.TeacherUpdateDTO;
import nl.codecafe.crud.models.Teacher;
import nl.codecafe.crud.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public TeacherDTO createTeacher(TeacherCreateDTO createDto) {
        Teacher teacher = createDto.toEntity();
        Teacher savedTeacher = teacherRepository.save(teacher);
        return TeacherDTO.fromEntity(savedTeacher);
    }

    public TeacherDTO updateTeacher(Long id, TeacherUpdateDTO updateDto) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Teacher not found with ID: " + id));

        updateDto.updateEntity(teacher);
        Teacher savedTeacher = teacherRepository.save(teacher);

        return TeacherDTO.fromEntity(savedTeacher);
    }

    public TeacherDTO findById(Long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Teacher not found with ID: " + id));

        return TeacherDTO.fromEntity(teacher);
    }

    public List<TeacherDTO> findAll() {
        return teacherRepository.findAll()
                .stream()
                .map(TeacherDTO::fromEntity)
                .toList();
    }

    public void deleteTeacher(Long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Teacher not found with ID: " + id));

        teacherRepository.delete(teacher);
    }
}
