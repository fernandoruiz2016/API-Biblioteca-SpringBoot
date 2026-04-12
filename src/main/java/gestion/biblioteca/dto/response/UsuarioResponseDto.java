package gestion.biblioteca.dto.response;

import java.time.LocalDateTime;

public record UsuarioResponseDto(
        Long idUsuario,
        String username,
        String email,
        Integer estado,

        String usuarioCreacion,
        String usuarioModificacion,
        LocalDateTime fechaCreacion,
        LocalDateTime fechaModificacion,
        String ipCreacion,
        String ipModificacion,

        Long idRol,
        String nombreRol
) {}
