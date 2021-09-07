package dev.patika.schoolmanagementsystem.business.criteriapermissions.concretes;

import dev.patika.schoolmanagementsystem.business.criteriapermissions.StudentCriteriaPermissions;
import dev.patika.schoolmanagementsystem.core.criteria.NotAllowedFilterCriteriaException;
import dev.patika.schoolmanagementsystem.core.specifications.criteria.FilterCriteria;
import dev.patika.schoolmanagementsystem.core.specifications.criteria.OperationType;
import dev.patika.schoolmanagementsystem.entities.Student_;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
class StudentCriteriaPermissionsImpl implements StudentCriteriaPermissions {

    private static final Map<String, List<OperationType>> filterCriteriaPermissions =
            new HashMap<String, List<OperationType>>() {{
                put(Student_.NAME, Collections.singletonList(OperationType.CONTAINS));
            }};

    @Override
    public void checkFilterCriteria(FilterCriteria criteria) {
        List<OperationType> operationTypes = filterCriteriaPermissions.get(criteria.getField());

        if (operationTypes == null)
            throw new NotAllowedFilterCriteriaException(criteria.getField());

        if (!operationTypes.contains(criteria.getOperationType()))
            throw new NotAllowedFilterCriteriaException(criteria.getField(), criteria.getOperationType());
    }
}
