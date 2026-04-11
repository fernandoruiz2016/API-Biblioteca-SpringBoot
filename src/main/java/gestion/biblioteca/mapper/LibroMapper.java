package gestion.biblioteca.mapper;

import gestion.biblioteca.dto.request.LibroRequestDto;
import gestion.biblioteca.dto.response.LibroResponseDto;
import gestion.biblioteca.entity.Libro;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LibroMapper {
    @Mapping(target = "idLibro", ignore = true)
    Libro toEntity(LibroRequestDto libroRequestDto);

    LibroResponseDto toResponse(Libro libro);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "idLibro", ignore = true)
    void updateFromRequest(LibroRequestDto libroRequestDto, @MappingTarget Libro libro);

    List<LibroResponseDto> toResponseList(List<Libro> libros);
}
