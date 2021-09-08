package dev.patika.schoolmanagementsystem.business.validation.validators;

import dev.patika.schoolmanagementsystem.business.validation.exceptions.StudentAgeNotValidException;
import dev.patika.schoolmanagementsystem.business.validation.rules.StudentValidationRules;
import dev.patika.schoolmanagementsystem.core.exceptions.NotAllowedFilterCriteriaException;
import dev.patika.schoolmanagementsystem.core.specifications.criteria.FilterCriteria;
import dev.patika.schoolmanagementsystem.core.specifications.criteria.OperationType;
import dev.patika.schoolmanagementsystem.core.utils.CriteriaPermissions;
import dev.patika.schoolmanagementsystem.entities.Student_;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class StudentValidator {

    private static final Map<String, List<OperationType>> filterCriteriaPermissions =
            new HashMap<String, List<OperationType>>() {{
                put(Student_.NAME, Collections.singletonList(OperationType.CONTAINS));
            }};

    /**
     * Checks whether querying is allowed based on parameters in the {@code criteria} object.
     *
     * @param criteria a {@link FilterCriteria} object containing query parameters.
     * @return {@code criteria} parameter.
     * @throws NotAllowedFilterCriteriaException if criteria is invalid.
     */
    public static FilterCriteria validateFilterCriteria(FilterCriteria criteria) {
        CriteriaPermissions.checkFilterCriteria(criteria, filterCriteriaPermissions);
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
