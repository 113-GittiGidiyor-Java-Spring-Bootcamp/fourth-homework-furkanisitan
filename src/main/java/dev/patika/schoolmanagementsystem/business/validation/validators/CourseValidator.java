package dev.patika.schoolmanagementsystem.business.validation.validators;

import dev.patika.schoolmanagementsystem.core.specifications.criteria.OperationType;
import dev.patika.schoolmanagementsystem.entities.Course_;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseValidator {

    public static final Map<String, List<OperationType>> filterCriteriaPermissions =
            new HashMap<String, List<OperationType>>() {{
                put(Course_.NAME, Collections.singletonList(OperationType.CONTAINS));
            }};

}
