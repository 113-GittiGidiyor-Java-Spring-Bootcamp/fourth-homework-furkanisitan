package dev.patika.schoolmanagementsystem.business.concretes;

import dev.patika.schoolmanagementsystem.business.StudentService;
import dev.patika.schoolmanagementsystem.business.dtos.StudentDto;
import dev.patika.schoolmanagementsystem.business.helpers.FilterCriteriaHelper;
import dev.patika.schoolmanagementsystem.business.mappers.StudentMapper;
import dev.patika.schoolmanagementsystem.business.validators.StudentValidator;
import dev.patika.schoolmanagementsystem.core.exceptions.EntityNotExistsException;
import dev.patika.schoolmanagementsystem.core.specifications.criteria.FilterCriteria;
import dev.patika.schoolmanagementsystem.dataaccess.StudentRepository;
import dev.patika.schoolmanagementsystem.dataaccess.specifications.StudentSpecification;
import dev.patika.schoolmanagementsystem.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
public class StudentManager implements StudentService {

    private final StudentRepository repository;

    @Autowired
    public StudentManager(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<StudentDto> findAll() {
        return StudentMapper.INSTANCE.toStudentDtoList(repository.findAll());
    }

    @Override
    public List<StudentDto> findAll(String filter) {

        List<FilterCriteria> criteria = FilterCriteriaHelper.from(filter);
        if (criteria.isEmpty()) return findAll();

        Specification<Student> spec = Specification.where(new StudentSpecification(StudentValidator.validateFilterCriteria(criteria.get(0))));
        for (int i = 1; i < criteria.size(); i++)
            spec.and(new StudentSpecification(StudentValidator.validateFilterCriteria(criteria.get(i))));

        return StudentMapper.INSTANCE.toStudentDtoList(repository.findAll(spec));
    }

    @Override
    public StudentDto findById(Long id) {
        return StudentMapper.INSTANCE.toStudentDto(repository.findById(id).orElse(null));
    }

    @Override
    public void deleteById(Long id) {

        // Check if the entity is exists.
        validateExistsById(id);

        repository.deleteById(id);
    }

    //region validators

    /**
     * Checks if there is a student with the given {@literal id}.
     *
     * @param id primary key of the entity.
     * @throws EntityNotExistsException if entity is not exists by {@literal id}.
     */
    private void validateExistsById(Long id) {
        if (!repository.existsById(id))
            throw new EntityNotExistsException("Student", Pair.of("id", id));
    }
    //endregion
}
