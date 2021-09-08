package dev.patika.schoolmanagementsystem.business.concretes;

import dev.patika.schoolmanagementsystem.business.CourseService;
import dev.patika.schoolmanagementsystem.business.dtos.CourseDto;
import dev.patika.schoolmanagementsystem.business.helpers.FilterCriteriaHelper;
import dev.patika.schoolmanagementsystem.business.mappers.CourseMapper;
import dev.patika.schoolmanagementsystem.business.validation.validators.CourseValidator;
import dev.patika.schoolmanagementsystem.business.validation.validators.FilterCriteriaValidator;
import dev.patika.schoolmanagementsystem.core.specifications.criteria.FilterCriteria;
import dev.patika.schoolmanagementsystem.dataaccess.CourseRepository;
import dev.patika.schoolmanagementsystem.dataaccess.specifications.CourseSpecification;
import dev.patika.schoolmanagementsystem.entities.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
public class CourseManager implements CourseService {

    private final CourseRepository repository;

    @Autowired
    public CourseManager(CourseRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CourseDto> findAll() {
        return CourseMapper.INSTANCE.toCourseDtoList(repository.findAll());
    }

    @Override
    public List<CourseDto> findAll(String filter) {
        List<FilterCriteria> criteria = FilterCriteriaHelper.from(filter);
        if (criteria.isEmpty()) return findAll();

        Specification<Course> spec = Specification.where(generateCourseSpecification(criteria.get(0)));

        for (int i = 1; i < criteria.size(); i++)
            spec.and(generateCourseSpecification(criteria.get(i)));

        return CourseMapper.INSTANCE.toCourseDtoList(repository.findAll(spec));
    }

    //region utils
    private CourseSpecification generateCourseSpecification(FilterCriteria criteria) {
        return new CourseSpecification(FilterCriteriaValidator.validateFilterCriteria(criteria, CourseValidator.filterCriteriaPermissions));
    }
    //endregion

}
