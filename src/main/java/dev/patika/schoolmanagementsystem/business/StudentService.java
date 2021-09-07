package dev.patika.schoolmanagementsystem.business;

import dev.patika.schoolmanagementsystem.business.dtos.StudentDto;
import dev.patika.schoolmanagementsystem.core.exceptions.EntityNotExistsException;

import java.util.List;

public interface StudentService {

    /**
     * Returns all students as {@link List<StudentDto>}.
     *
     * @return a {@link List<StudentDto>}.
     */
    List<StudentDto> findAll();

    /**
     * Returns all students as {@link List<StudentDto>} by {@literal filter}.
     *
     * @param filter a text containing filter parameters.
     * @return a {@link List<StudentDto>} by {@literal filter}.
     */
    List<StudentDto> findAll(String filter);

    /**
     * Returns a student as {@link StudentDto} by {@literal id}.
     *
     * @param id the primary key of the entity.
     * @return a {@link StudentDto} by {@literal id}.
     */
    StudentDto findById(Long id);

    /**
     * Deletes student by {@literal id}.
     *
     * @param id the primary key of the entity.
     * @throws EntityNotExistsException if entity is not exists by {@literal id}.
     */
    void deleteById(Long id);

}
