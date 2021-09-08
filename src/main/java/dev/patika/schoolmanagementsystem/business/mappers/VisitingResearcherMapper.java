package dev.patika.schoolmanagementsystem.business.mappers;

import dev.patika.schoolmanagementsystem.business.dtos.VisitingResearcherDto;
import dev.patika.schoolmanagementsystem.entities.VisitingResearcher;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface VisitingResearcherMapper {

    VisitingResearcherMapper INSTANCE = Mappers.getMapper(VisitingResearcherMapper.class);

    List<VisitingResearcherDto> toInstructorDtoList(List<VisitingResearcher> VisitingResearchers);

    VisitingResearcherDto toVisitingResearcherDto(VisitingResearcher visitingResearcher);
}
