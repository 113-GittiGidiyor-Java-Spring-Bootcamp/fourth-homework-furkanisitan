package dev.patika.schoolmanagementsystem.business;

import dev.patika.schoolmanagementsystem.business.criteria.InstructorCriteria;
import dev.patika.schoolmanagementsystem.business.dtos.InstructorDto;
import dev.patika.schoolmanagementsystem.entities.Instructor;

import java.util.List;

public interface InstructorService {

    /**
     * Returns all instructors as {@link List<InstructorDto>} by {@literal filter} and {@literal criteria}.
     *
     * @param filter a text containing filter parameters.
     * @return a {@link List<InstructorDto>} by {@literal filter} and {@literal criteria}.
     */
    List<InstructorDto> findAll(String filter, InstructorCriteria criteria);


    /**
     * Returns a instructor as {@link InstructorDto} by {@literal id}.
     *
     * @param id the primary key of the entity.
     * @return a {@link InstructorDto} by {@literal id}.
     */
    InstructorDto findById(Long id);

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
