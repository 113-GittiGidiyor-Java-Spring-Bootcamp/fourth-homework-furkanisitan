package dev.patika.schoolmanagementsystem.core.specifications.exceptions;

import dev.patika.schoolmanagementsystem.core.specifications.BaseSpecification;
import dev.patika.schoolmanagementsystem.core.specifications.criteria.FilterCriteria;
import lombok.Getter;

/**
 * Thrown to indicate that the {@link FilterCriteria} parameter passed to the constructor
 * of the {@link BaseSpecification} contains an invalid {@link FilterCriteria#getField()}.
 */

@Getter
public class InvalidFieldException extends RuntimeException {

    private final String field;

    /**
     * Constructs an {@link InvalidFieldException}.
     *
     * @param field the name of the invalid field.
     */
    public InvalidFieldException(String field) {
        super(String.format("The '%s' field is invalid.", field));
        this.field = field;
    }
}
