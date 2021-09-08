package dev.patika.schoolmanagementsystem.business.mappers;

import dev.patika.schoolmanagementsystem.business.dtos.InstructorCreateDto;
import dev.patika.schoolmanagementsystem.business.dtos.InstructorDto;
import dev.patika.schoolmanagementsystem.business.dtos.PermanentInstructorCreateDto;
import dev.patika.schoolmanagementsystem.business.dtos.VisitingResearcherCreateDto;
import dev.patika.schoolmanagementsystem.entities.Instructor;
import dev.patika.schoolmanagementsystem.entities.PermanentInstructor;
import dev.patika.schoolmanagementsystem.entities.VisitingResearcher;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface InstructorMapper {

    InstructorMapper INSTANCE = Mappers.getMapper(InstructorMapper.class);

    @IterableMapping(qualifiedByName = "toInstructorDto")
    List<InstructorDto> toInstructorDtoList(List<? extends Instructor> instructors);

    InstructorDto instructorToInstructorDto(Instructor instructor);

    @Named(value = "toInstructorDto")
    default InstructorDto toInstructorDto(Instructor instructor) {
        if (instructor instanceof PermanentInstructor)
            return PermanentInstructorMapper.INSTANCE.toPermanentInstructorDto((PermanentInstructor) instructor);
        else if (instructor instanceof VisitingResearcher)
            return VisitingResearcherMapper.INSTANCE.toVisitingResearcherDto((VisitingResearcher) instructor);
        return InstructorMapper.INSTANCE.instructorToInstructorDto(instructor);
    }

    Instructor instructorFromInstructorCreateDto(InstructorCreateDto instructorCreateDto);

    @Named(value = "fromInstructorCreateDto")
    default Instructor fromInstructorCreateDto(InstructorCreateDto instructorCreateDto) {
        if (instructorCreateDto instanceof PermanentInstructorCreateDto)
            return PermanentInstructorMapper.INSTANCE.fromPermanentInstructorCreateDto((PermanentInstructorCreateDto) instructorCreateDto);
        else if (instructorCreateDto instanceof VisitingResearcherCreateDto)
            return VisitingResearcherMapper.INSTANCE.fromVisitingResearcherCreateDto((VisitingResearcherCreateDto) instructorCreateDto);
        return InstructorMapper.INSTANCE.instructorFromInstructorCreateDto(instructorCreateDto);
    }

}
