package dev.patika.schoolmanagementsystem.business;

import dev.patika.schoolmanagementsystem.business.dtos.CourseDto;

import java.util.List;

public interface CourseService {

    /**
     * Returns all courses as {@link List<CourseDto>}.
     *
     * @return a {@link List<CourseDto>}.
     */
    List<CourseDto> findAll();

    /**
     * Returns all courses as {@link List<CourseDto>} by {@literal filter}.
     *
     * @param filter a text containing filter parameters.
     * @return a {@link List<CourseDto>} by {@literal filter}.
     */
    List<CourseDto> findAll(String filter);

}
