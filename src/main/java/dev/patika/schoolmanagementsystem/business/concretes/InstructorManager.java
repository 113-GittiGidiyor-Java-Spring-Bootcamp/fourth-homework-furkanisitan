package dev.patika.schoolmanagementsystem.business.concretes;

import dev.patika.schoolmanagementsystem.business.InstructorService;
import dev.patika.schoolmanagementsystem.business.criteria.InstructorCriteria;
import dev.patika.schoolmanagementsystem.business.dtos.InstructorDto;
import dev.patika.schoolmanagementsystem.business.helpers.FilterCriteriaHelper;
import dev.patika.schoolmanagementsystem.business.mappers.InstructorMapper;
import dev.patika.schoolmanagementsystem.business.validation.validators.FilterCriteriaValidator;
import dev.patika.schoolmanagementsystem.business.validation.validators.InstructorValidator;
import dev.patika.schoolmanagementsystem.core.specifications.criteria.FilterCriteria;
import dev.patika.schoolmanagementsystem.dataaccess.InstructorRepository;
import dev.patika.schoolmanagementsystem.dataaccess.PermanentInstructorRepository;
import dev.patika.schoolmanagementsystem.dataaccess.VisitingResearcherRepository;
import dev.patika.schoolmanagementsystem.dataaccess.specifications.InstructorSpecification;
import dev.patika.schoolmanagementsystem.entities.Instructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
class InstructorManager implements InstructorService {

    private final InstructorRepository repository;
    private final PermanentInstructorRepository permanentInstructorRepository;
    private final VisitingResearcherRepository visitingResearcherRepository;

    @Autowired
    public InstructorManager(InstructorRepository repository, PermanentInstructorRepository permanentInstructorRepository, VisitingResearcherRepository visitingResearcherRepository) {
        this.repository = repository;
        this.permanentInstructorRepository = permanentInstructorRepository;
        this.visitingResearcherRepository = visitingResearcherRepository;
    }

    @Override
    public List<InstructorDto> findAll(String filter, InstructorCriteria criteria) {

        List<FilterCriteria> filterCriteria = FilterCriteriaHelper.from(filter);
        Specification<Instructor> spec = generateInstructorSpecification(filterCriteria);

        // Filter by valid limit or sort criteria
        PageRequest pageRequest = criteria.generatePageRequest();
        Sort sort = criteria.generateSort();

        if (sort != null) {
            JpaRepository<? extends Instructor, Long> repo = getRepositoryForSort(criteria.getSort());
            return InstructorMapper.INSTANCE.toInstructorDtoList(pageRequest == null ? repo.findAll(sort) : repo.findAll(pageRequest.withSort(sort)).getContent());
        }

        return InstructorMapper.INSTANCE.toInstructorDtoList(pageRequest == null ? repository.findAll(spec) : repository.findAll(spec, pageRequest).getContent());
    }

    @Override
    public InstructorDto findById(Long id) {
        return InstructorMapper.INSTANCE.toInstructorDto(repository.findById(id).orElse(null));
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
    private Specification<Instructor> generateInstructorSpecification(List<FilterCriteria> criteria) {

        if (criteria.isEmpty())
            return null;

        Specification<Instructor> spec = Specification.where(new InstructorSpecification(FilterCriteriaValidator.validateFilterCriteria(criteria.get(0), InstructorValidator.filterCriteriaPermissions)));

        for (int i = 1; i < criteria.size(); i++)
            spec.and(new InstructorSpecification(FilterCriteriaValidator.validateFilterCriteria(criteria.get(i), InstructorValidator.filterCriteriaPermissions)));

        return spec;
    }

    private JpaRepository<? extends Instructor, Long> getRepositoryForSort(String sort) {
        if (sort.contains("fixedSalary")) return permanentInstructorRepository;
        if (sort.contains("hourlySalary")) return visitingResearcherRepository;
        return repository;
    }
    //endregion
}
