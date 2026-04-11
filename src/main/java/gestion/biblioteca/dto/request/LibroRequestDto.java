package gestion.biblioteca.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LibroRequestDto {
    @NotBlank(message = "El título no puede estar vacío")
    @Size(max = 150, message = "El título no puede exceder los 150 caracteres")
    private String titulo;

    @NotBlank(message = "El autor es obligatorio")
    @Size(max = 100, message = "El nombre del autor no puede exceder los 100 caracteres")
    private String autor;

    @NotNull(message = "El stock no puede ser nulo")
    @Min(value = 0, message = "El stock no puede ser negativo")
    private Integer stock;

    @PastOrPresent(message = "La fecha de publicación no puede ser futura")
    private LocalDate fechaPublicacion;

    @NotNull(message = "El estado es obligatorio")
    @Min(0)
    @Max(1)
    private Integer estado;
}
