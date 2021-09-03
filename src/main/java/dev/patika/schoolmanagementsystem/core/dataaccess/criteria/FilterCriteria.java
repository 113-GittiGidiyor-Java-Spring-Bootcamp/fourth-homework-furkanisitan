package dev.patika.schoolmanagementsystem.core.dataaccess.criteria;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Contains the required fields for queries that will be built using the {@link org.springframework.data.jpa.domain.Specification}.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilterCriteria {

    private String field;
    private OperationType operationType;
    private String value;
}
