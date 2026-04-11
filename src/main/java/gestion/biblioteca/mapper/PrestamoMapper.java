package gestion.biblioteca.mapper;

import gestion.biblioteca.dto.request.PrestamoRequestDto;
import gestion.biblioteca.dto.response.PrestamoResponseDto;
import gestion.biblioteca.entity.Prestamo;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PrestamoMapper {
    @Mapping(target = "idPrestamo", ignore = true)
    @Mapping(target = "libro", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    Prestamo toEntity(PrestamoRequestDto prestamoRequestDto);

    @Mapping(source = "libro.idLibro", target = "idLibro")
    @Mapping(source = "libro.titulo", target = "tituloLibro")
    @Mapping(source = "usuario.idUsuario", target = "idUsuario")
    @Mapping(source = "usuario.apellido", target = "apellidoUsuario")
    PrestamoResponseDto toResponse(Prestamo prestamo);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "idPrestamo", ignore = true)
    @Mapping(target = "libro", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    void updateFromRequest(PrestamoRequestDto prestamoRequestDto, @MappingTarget Prestamo prestamo);

    List<PrestamoResponseDto> toResponseList(List<Prestamo> prestamos);
}
