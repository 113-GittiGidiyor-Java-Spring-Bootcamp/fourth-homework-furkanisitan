package dev.patika.schoolmanagementsystem.core.dataaccess.specifications.exceptions;

import dev.patika.schoolmanagementsystem.core.dataaccess.criteria.FilterCriteria;
import dev.patika.schoolmanagementsystem.core.dataaccess.specifications.BaseSpecification;
import lombok.Getter;

/**
 * Thrown to indicate that the {@link FilterCriteria} parameter passed to the constructor
 * of the {@link BaseSpecification} contains an invalid {@link FilterCriteria#getValue()}
 * that cannot be parsed for {@link FilterCriteria#getField()}.
 */

@Getter
public class InvalidValueException extends RuntimeException {

    private final String field;
    private final String value;

    /**
     * Constructs an {@link InvalidValueException}.
     *
     * @param field the name of the field for which the value is invalid.
     * @param value the invalid value.
     */
    public InvalidValueException(String field, String value) {
        super(String.format("The value '%s' for the '%s' field is invalid.", value, field));
        this.field = field;
        this.value = value;
    }
}
