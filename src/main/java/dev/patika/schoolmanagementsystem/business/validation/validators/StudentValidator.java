package dev.patika.schoolmanagementsystem.business.validation.validators;

import dev.patika.schoolmanagementsystem.business.criteriapermissions.StudentCriteriaPermissions;
import dev.patika.schoolmanagementsystem.business.validation.exceptions.StudentAgeNotValidException;
import dev.patika.schoolmanagementsystem.business.validation.rules.StudentValidationRules;
import dev.patika.schoolmanagementsystem.core.criteria.NotAllowedFilterCriteriaException;
import dev.patika.schoolmanagementsystem.core.specifications.criteria.FilterCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

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

    /**
     * Checks the student's age range.
     *
     * @param birthDate student's date of birth
     * @throws StudentAgeNotValidException if student's age is not within the determined range.
     */
    public static void validateAge(LocalDate birthDate) {

        int age = -1;

        if (birthDate != null)
            age = Period.between(birthDate, LocalDate.now()).getYears();

        if (age < StudentValidationRules.MIN_AGE || age > StudentValidationRules.MAX_AGE)
            throw new StudentAgeNotValidException(String.format("Student age must be between %s and %s.", StudentValidationRules.MIN_AGE, StudentValidationRules.MAX_AGE));
    }
}
