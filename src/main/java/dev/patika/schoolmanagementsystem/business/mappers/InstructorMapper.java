package dev.patika.schoolmanagementsystem.business.mappers;

import dev.patika.schoolmanagementsystem.business.dtos.InstructorDto;
import dev.patika.schoolmanagementsystem.entities.Instructor;
import dev.patika.schoolmanagementsystem.entities.PermanentInstructor;
import dev.patika.schoolmanagementsystem.entities.VisitingResearcher;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface InstructorMapper {

    InstructorMapper INSTANCE = Mappers.getMapper(InstructorMapper.class);

    default List<? extends InstructorDto> toInstructorDtoList(List<? extends Instructor> instructors) {
        return instructors.stream().map(x -> {
            if (x instanceof PermanentInstructor)
                return PermanentInstructorMapper.INSTANCE.toPermanentInstructorDto((PermanentInstructor) x);
            else if (x instanceof VisitingResearcher)
                return VisitingResearcherMapper.INSTANCE.toVisitingResearcherDto((VisitingResearcher) x);
            return InstructorMapper.INSTANCE.toInstructorDto(x);
        }).collect(Collectors.toList());
    }

    InstructorDto toInstructorDto(Instructor instructor);

}
