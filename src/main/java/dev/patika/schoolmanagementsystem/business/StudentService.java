package dev.patika.schoolmanagementsystem.business;

import dev.patika.schoolmanagementsystem.business.dtos.StudentDto;

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
}
