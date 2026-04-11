package gestion.biblioteca.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrestamoRequestDto {
    @NotNull(message = "El ID del libro es obligatorio")
    private Long idLibro;

    @NotNull(message = "El ID del usuario es obligatorio")
    private Long idUsuario;

    @NotNull(message = "La fecha de préstamo es obligatoria")
    @PastOrPresent(message = "La fecha de préstamo no puede ser futura")
    private LocalDateTime fechaPrestamo;

    @NotNull(message = "La fecha de devolución esperada es obligatoria")
    @Future(message = "La fecha de devolución esperada debe ser una fecha futura")
    private LocalDateTime fechaDevolucionEsperada;

    private LocalDateTime fechaDevolucionReal;

    @NotNull(message = "El estado del préstamo es obligatorio")
    @Min(1) @Max(3)
    private Integer estado; // 1: Activo, 2: Devuelto, 3: Moroso
}
