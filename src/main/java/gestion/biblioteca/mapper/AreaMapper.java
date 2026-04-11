package gestion.biblioteca.mapper;

import org.mapstruct.*;
import gestion.biblioteca.dto.request.AreaRequestDto;
import gestion.biblioteca.dto.response.AreaResponseDto;
import gestion.biblioteca.entity.Area;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AreaMapper {

    @Mapping(target = "idArea", ignore = true)
    Area toEntity(AreaRequestDto areaRequestDto);

    AreaResponseDto toResponse(Area area);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "idArea", ignore = true)
    void updateFromRequest(AreaRequestDto areaRequestDto, @MappingTarget Area area);

    List<AreaResponseDto> toResponseList(List<Area> areas);
}
