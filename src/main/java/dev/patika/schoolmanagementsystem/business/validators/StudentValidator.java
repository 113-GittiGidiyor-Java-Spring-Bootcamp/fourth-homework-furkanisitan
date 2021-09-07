package dev.patika.schoolmanagementsystem.business.validators;

import dev.patika.schoolmanagementsystem.business.criteriapermissions.StudentCriteriaPermissions;
import dev.patika.schoolmanagementsystem.core.specifications.criteria.FilterCriteria;
import dev.patika.schoolmanagementsystem.core.criteria.NotAllowedFilterCriteriaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentValidator {

    private static StudentCriteriaPermissions permissions;

    @Autowired
    public StudentValidator(StudentCriteriaPermissions permissions) {
        StudentValidator.permissions = permissions;
    }

    /**
     * Checks whether querying is allowed based on parameters in the {@code criteria} object.
     *
     * @param criteria a {@link FilterCriteria} object containing query parameters.
     * @return {@code criteria} parameter.
     * @throws NotAllowedFilterCriteriaException if criteria is invalid.
     */
    public static FilterCriteria validateFilterCriteria(FilterCriteria criteria) {
        permissions.checkFilterCriteria(criteria);
        return criteria;
    }
}
