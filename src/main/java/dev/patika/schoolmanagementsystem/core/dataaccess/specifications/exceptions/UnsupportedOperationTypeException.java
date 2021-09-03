package dev.patika.schoolmanagementsystem.core.dataaccess.specifications.exceptions;

import dev.patika.schoolmanagementsystem.core.dataaccess.specifications.BaseSpecification;
import dev.patika.schoolmanagementsystem.core.dataaccess.criteria.FilterCriteria;
import dev.patika.schoolmanagementsystem.core.dataaccess.criteria.OperationType;
import lombok.Getter;

/**
 * Thrown to indicate that the {@link FilterCriteria} parameter passed to the constructor
 * of the {@link BaseSpecification} contains an unsupported {@link FilterCriteria#getOperationType()}.
 */

@Getter
public class UnsupportedOperationTypeException extends RuntimeException {

    private final OperationType operationType;

    /**
     * Constructs an {@link UnsupportedOperationTypeException}.
     *
     * @param operationType unsupported operation type.
     */
    public UnsupportedOperationTypeException(OperationType operationType) {
        super(String.format("The '%s' operator is an unsupported operation type.", operationType.getShortcut()));
        this.operationType = operationType;
    }
}

