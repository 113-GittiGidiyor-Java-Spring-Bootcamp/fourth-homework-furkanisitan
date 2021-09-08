package dev.patika.schoolmanagementsystem.business;

import dev.patika.schoolmanagementsystem.business.dtos.InstructorDto;
import dev.patika.schoolmanagementsystem.entities.Instructor;

import java.util.List;

public interface InstructorService {

    /**
     * Returns all instructors as {@link List<InstructorDto>}.
     *
     * @return a {@link List<InstructorDto>}.
     */
    List<? extends InstructorDto> findAll();

    /**
     * Returns all instructors as {@link List<InstructorDto>} by {@literal filter}.
     *
     * @param filter a text containing filter parameters.
     * @return a {@link List<InstructorDto>} by {@literal filter}.
     */
    List<? extends InstructorDto> findAll(String filter);

    /**
     * @param id the primary key of the entity.
     * @return proxy object of Instructor by {@literal id}.
     */
    Instructor getById(Long id);

    /**
     * @param id the primary key of the entity.
     * @return true if instructor exists by {@literal id}, otherwise false.
     */
    boolean existsById(Long id);
}
