package dev.patika.schoolmanagementsystem.business.mappers;

import dev.patika.schoolmanagementsystem.business.dtos.PermanentInstructorDto;
import dev.patika.schoolmanagementsystem.entities.PermanentInstructor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PermanentInstructorMapper {

    PermanentInstructorMapper INSTANCE = Mappers.getMapper(PermanentInstructorMapper.class);

    List<PermanentInstructorDto> toInstructorDtoList(List<PermanentInstructor> permanentInstructors);

    PermanentInstructorDto toPermanentInstructorDto(PermanentInstructor permanentInstructor);
}
