package gestion.biblioteca.mapper;

import gestion.biblioteca.dto.request.UsuarioRequestDto;
import gestion.biblioteca.dto.response.UsuarioResponseDto;
import gestion.biblioteca.entity.Rol;
import gestion.biblioteca.entity.Usuario;
import org.mapstruct.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    @Mapping(target = "idUsuario", ignore = true)
    @Mapping(target = "roles", source = "roles")
    Usuario toEntity(UsuarioRequestDto usuarioRequestDto);

    @Mapping(target = "roles", source = "roles")
    UsuarioResponseDto toResponse(Usuario usuario);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "idUsuario", ignore = true)
    @Mapping(target = "roles", ignore = true)
    void updateFromRequest(UsuarioRequestDto usuarioRequestDto, @MappingTarget Usuario usuario);

    List<UsuarioResponseDto> toResponseList(List<Usuario> usuarios);

    // Metodos para el Set><Rol>
    default Set<Rol> mapStringToRolSet(Set<String> roles) {
        if (roles == null) return null;
        return roles.stream().map(nombre -> {
            Rol rol = new Rol();
            rol.setNombre(nombre);
            return rol;
        }).collect(Collectors.toSet());
    }

    default Set<String> mapRolToStringSet(Set<Rol> roles) {
        if (roles == null) return null;
        return roles.stream()
                .map(Rol::getNombre)
                .collect(Collectors.toSet());
    }
}
