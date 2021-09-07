package dev.patika.schoolmanagementsystem.business;

import dev.patika.schoolmanagementsystem.business.dtos.StudentDto;

import java.util.List;

public interface StudentService {

    /**
     * Returns all students as {@link List<StudentDto>}.
     *
     * @return all students.
     */
    List<StudentDto> findAll();

    /**
     * Returns all students by filter as {@link List<StudentDto>}.
     *
     * @param filter a text containing filter parameters.
     * @return all students by filter.
     */
    List<StudentDto> findAll(String filter);
}
