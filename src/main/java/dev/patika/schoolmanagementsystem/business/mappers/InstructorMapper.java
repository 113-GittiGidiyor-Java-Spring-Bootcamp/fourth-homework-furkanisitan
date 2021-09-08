package dev.patika.schoolmanagementsystem.business.mappers;

import dev.patika.schoolmanagementsystem.business.dtos.InstructorDto;
import dev.patika.schoolmanagementsystem.entities.Instructor;
import dev.patika.schoolmanagementsystem.entities.PermanentInstructor;
import dev.patika.schoolmanagementsystem.entities.VisitingResearcher;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface InstructorMapper {

    InstructorMapper INSTANCE = Mappers.getMapper(InstructorMapper.class);

    List<InstructorDto> toInstructorDtoList(List<? extends Instructor> instructors);

    default InstructorDto toInstructorDto(Instructor instructor) {
        if (instructor instanceof PermanentInstructor)
            return PermanentInstructorMapper.INSTANCE.toPermanentInstructorDto((PermanentInstructor) instructor);
        else if (instructor instanceof VisitingResearcher)
            return VisitingResearcherMapper.INSTANCE.toVisitingResearcherDto((VisitingResearcher) instructor);
        return InstructorMapper.INSTANCE.toInstructorDto(instructor);
    }

}
