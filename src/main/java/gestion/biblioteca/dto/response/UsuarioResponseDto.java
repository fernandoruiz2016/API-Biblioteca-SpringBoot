package gestion.biblioteca.dto.response;

import java.time.LocalDateTime;
import java.util.Set;

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

        Set<String> roles
) {}
