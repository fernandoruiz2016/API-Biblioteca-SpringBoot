package gestion.biblioteca.dto.response;

import java.time.LocalDateTime;

public record PrestamoResponseDto(
        Long idPrestamo,

        Long idLibro,
        String tituloLibro,

        Long idUsuario,
        String usernameUsuario,

        LocalDateTime fechaPrestamo,
        LocalDateTime fechaDevolucionEsperada,
        LocalDateTime fechaDevolucionReal,
        Integer estado,

        String usuarioCreacion,
        String usuarioModificacion,
        LocalDateTime fechaCreacion,
        LocalDateTime fechaModificacion,
        String ipCreacion,
        String ipModificacion
) {
    
}
