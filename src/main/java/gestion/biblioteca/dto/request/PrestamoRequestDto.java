package gestion.biblioteca.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Schema(description= "DTO para las solicitudes de Préstamos")
public record PrestamoRequestDto(
        @NotNull(message = "El ID del libro es obligatorio")
        @Schema(description = "N° de ID del libro", example = "5")
        Long idLibro,

        @NotNull(message = "El ID del usuario es obligatorio")
        @Schema(description = "N° de ID del usuario", example = "2")
        Long idUsuario,
        
        @NotNull(message = "La fecha de préstamo es obligatoria")
        @PastOrPresent(message = "La fecha de préstamo no puede ser futura")
        @Schema(description = "Fecha y hora en que se entrega el libro", example = "2026-04-12T15:30:00")
        LocalDateTime fechaPrestamo,
        
        @NotNull(message = "La fecha de devolución esperada es obligatoria")
        @Future(message = "La fecha de devolución esperada debe ser una fecha futura")
        @Schema(description = "Fecha límite pactada para la devolución", example = "2026-04-19T18:00:00")
        LocalDateTime fechaDevolucionEsperada,

        @Schema(description = "Fecha real en la que el usuario devolvió el libro (opcional al crear)", example = "null")
        LocalDateTime fechaDevolucionReal,

        @Min(0) @Max(2)
        @Schema(description = "Estado actual del préstamo (1: Activo, 2: Devuelto, 3: Moroso)", example = "1")
        Integer estado // 0:Inactivo, 1: Activo, 2: Devuelto
) {
}
