package gestion.biblioteca.mapper;

import gestion.biblioteca.dto.request.UsuarioRequestDto;
import gestion.biblioteca.dto.response.UsuarioResponseDto;
import gestion.biblioteca.entity.Usuario;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    @Mapping(target = "idUsuario", ignore = true)
    Usuario toEntity(UsuarioRequestDto usuarioRequestDto);

    UsuarioResponseDto toResponse(Usuario usuario);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "idUsuario", ignore = true)
    void updateFromRequest(UsuarioRequestDto usuarioRequestDto, @MappingTarget Usuario usuario);

    List<UsuarioResponseDto> toResponseList(List<Usuario> usuarios);
}
