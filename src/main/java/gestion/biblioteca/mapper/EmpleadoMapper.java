package gestion.biblioteca.mapper;

import org.mapstruct.*;
import gestion.biblioteca.dto.request.EmpleadoRequestDto;
import gestion.biblioteca.dto.response.EmpleadoResponseDto;
import gestion.biblioteca.entity.Empleado;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmpleadoMapper {

    @Mapping(target = "idEmpleado", ignore = true)
    @Mapping(target = "idAfp", ignore = true)
    @Mapping(target = "idArea", ignore = true)
    Empleado toEntity(EmpleadoRequestDto empleadoRequestDto);

    @Mapping(target = "idAfp", source = "afp.idAfp")
    @Mapping(target = "idArea", source = "area.idArea")
    EmpleadoResponseDto toResponse(Empleado empleado);

    List<EmpleadoResponseDto> toResponseList(List<Empleado> empleados);
}
