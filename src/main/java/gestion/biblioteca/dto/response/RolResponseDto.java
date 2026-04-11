package gestion.biblioteca.dto.response;

import java.time.LocalDateTime;

public record RolResponseDto(
        Long idRol,
        String nombre,
        String descripcion,
        Integer estado,

        String usuarioCreacion,
        String usuarioModificacion,
        LocalDateTime fechaCreacion,
        LocalDateTime fechaModificacion,
        String ipCreacion,
        String ipModificacion
) {}
    
