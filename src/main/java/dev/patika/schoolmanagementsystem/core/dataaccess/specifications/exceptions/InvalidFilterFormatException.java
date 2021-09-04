package dev.patika.schoolmanagementsystem.core.dataaccess.specifications.exceptions;

import dev.patika.schoolmanagementsystem.core.dataaccess.specifications.criteria.FilterCriteria;
import lombok.Getter;

/**
 * Thrown to indicate that the {@code filter} given to the {@link FilterCriteria#valueOf(String filter, String regex)} method
 * does not match the {@code regex}.
 */

@Getter
public class InvalidFilterFormatException extends RuntimeException {

    private final String filter;

    public InvalidFilterFormatException(String filter) {
        super(String.format("The text '%s' is not in the required format.", filter));
        this.filter = filter;
    }
}