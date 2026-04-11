package gestion.biblioteca.mapper;

import gestion.biblioteca.dto.request.RolRequestDto;
import gestion.biblioteca.dto.response.RolResponseDto;
import gestion.biblioteca.entity.Rol;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RolMapper {
    @Mapping(target = "idRol", ignore = true)
    Rol toEntity(RolRequestDto rolRequestDto);

    RolResponseDto toResponse(Rol rol);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "idRol", ignore = true)
    void updateFromRequest(RolRequestDto rolRequestDto, @MappingTarget Rol rol);

    List<RolResponseDto> toResponseList(List<Rol> roles);
}
