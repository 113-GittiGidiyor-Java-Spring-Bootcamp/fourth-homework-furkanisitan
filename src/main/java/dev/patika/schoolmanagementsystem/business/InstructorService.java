package dev.patika.schoolmanagementsystem.business;

import dev.patika.schoolmanagementsystem.entities.Instructor;

public interface InstructorService {

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
