package dev.patika.schoolmanagementsystem.core.dataaccess.specifications.criteria;

import dev.patika.schoolmanagementsystem.core.dataaccess.specifications.exceptions.InvalidFilterFormatException;
import dev.patika.schoolmanagementsystem.core.dataaccess.specifications.exceptions.UnsupportedOperationTypeException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    /**
     * Processes {@code filter} parameter according to {@code regex} and converts it to {@link FilterCriteria} list.
     *
     * @param filter a text containing the filter parameters.
     * @param regex  a regular expression to match to convert text to a {@link FilterCriteria} object.
     * @return a list of {@link FilterCriteria}.
     * @throws InvalidFilterFormatException      if all the text of the filter does not match the regex.
     * @throws UnsupportedOperationTypeException if any unsupported operation type inside the filter.
     * @throws IllegalArgumentException          if filter or regex is {@literal null}.
     */
    public static List<FilterCriteria> valueOf(String filter, String regex) {
        Assert.notNull(filter, "text must not be null");
        Assert.notNull(regex, "regex must not be null");

        Pattern one = Pattern.compile(regex, Pattern.UNICODE_CHARACTER_CLASS);
        Pattern all = Pattern.compile("(" + regex + ")+", Pattern.UNICODE_CHARACTER_CLASS);

        Matcher matcher = all.matcher(filter);

        if (matcher.matches()) {

            List<FilterCriteria> criteria = new ArrayList<>();
            matcher = one.matcher(filter);

            while (matcher.find()) {
                OperationType operationType = OperationType.valueOfShortcut(matcher.group(2));
                if (operationType == null)
                    throw new UnsupportedOperationTypeException(matcher.group(2));
                criteria.add(new FilterCriteria(matcher.group(1), operationType, matcher.group(3)));
            }

            return criteria;
        } else {
            throw new InvalidFilterFormatException(filter);
        }
    }
}
