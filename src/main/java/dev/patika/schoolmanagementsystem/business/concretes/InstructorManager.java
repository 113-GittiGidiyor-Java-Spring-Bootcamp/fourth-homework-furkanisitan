package dev.patika.schoolmanagementsystem.business.concretes;

import dev.patika.schoolmanagementsystem.business.InstructorService;
import dev.patika.schoolmanagementsystem.business.dtos.InstructorDto;
import dev.patika.schoolmanagementsystem.business.helpers.FilterCriteriaHelper;
import dev.patika.schoolmanagementsystem.business.mappers.InstructorMapper;
import dev.patika.schoolmanagementsystem.business.validation.validators.FilterCriteriaValidator;
import dev.patika.schoolmanagementsystem.business.validation.validators.InstructorValidator;
import dev.patika.schoolmanagementsystem.core.specifications.criteria.FilterCriteria;
import dev.patika.schoolmanagementsystem.dataaccess.InstructorRepository;
import dev.patika.schoolmanagementsystem.dataaccess.specifications.InstructorSpecification;
import dev.patika.schoolmanagementsystem.entities.Instructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
class InstructorManager implements InstructorService {

    private final InstructorRepository repository;

    @Autowired
    public InstructorManager(InstructorRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<? extends InstructorDto> findAll() {
        return InstructorMapper.INSTANCE.toInstructorDtoList(repository.findAll());
    }

    @Override
    public List<? extends InstructorDto> findAll(String filter) {

        List<FilterCriteria> criteria = FilterCriteriaHelper.from(filter);
        if (criteria.isEmpty()) return findAll();

        Specification<Instructor> spec = Specification.where(generateInstructorSpecification(criteria.get(0)));

        for (int i = 1; i < criteria.size(); i++)
            spec.and(generateInstructorSpecification(criteria.get(i)));

        return InstructorMapper.INSTANCE.toInstructorDtoList(repository.findAll(spec));
    }

    @Override
    public Instructor getById(Long id) {
        return repository.getById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    //region utils
    private InstructorSpecification generateInstructorSpecification(FilterCriteria criteria) {
        return new InstructorSpecification(FilterCriteriaValidator.validateFilterCriteria(criteria, InstructorValidator.filterCriteriaPermissions));
    }
    //endregion
}
