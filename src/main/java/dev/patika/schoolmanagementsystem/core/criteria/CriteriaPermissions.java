package dev.patika.schoolmanagementsystem.core.criteria;

import dev.patika.schoolmanagementsystem.core.specifications.criteria.FilterCriteria;

public interface CriteriaPermissions<T> {

    /**
     * This method checks for the following two cases.
     * 1. Are {getField()} and {getOperationType()} in the {criteria} parameter valid for {@link T}?
     * 2. Is a query of type {OperationType()} allowed for {Field()}?
     *
     * @param criteria a {@link FilterCriteria} object containing query parameters.
     * @throws NotAllowedFilterCriteriaException if criteria is invalid.
     */
    void checkFilterCriteria(FilterCriteria criteria);

}
