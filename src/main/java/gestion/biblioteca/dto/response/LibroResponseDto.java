package gestion.biblioteca.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record LibroResponseDto(
        Long idLibro,
        String titulo,
        String autor,
        Integer stock,
        LocalDate fechaPublicacion,
        Integer estado,

        String usuarioCreacion,
        String usuarioModificacion,
        LocalDateTime fechaCreacion,
        LocalDateTime fechaModificacion,
        String ipCreacion,
        String ipModificacion
) {}
