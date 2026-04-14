package gestion.biblioteca.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Schema(description= "DTO para las solicitudes de Libros")
public record LibroRequestDto(
        @NotBlank(message = "El título no puede estar vacío")
        @Size(max = 150, message = "El título no puede exceder los 150 caracteres")
        @Schema(description = "Título del libro", example = "Cien años de soledad")
        String titulo,

        @NotBlank(message = "El autor es obligatorio")
        @Size(max = 100, message = "El nombre del autor no puede exceder los 100 caracteres")
        @Schema(description = "Nombre del autor", example = "Gabriel García Márquez")
        String autor,
        
        @NotNull(message = "El stock no puede ser nulo")
        @Min(value = 0, message = "El stock no puede ser negativo")
        @Schema(description = "Cantidad de ejemplares disponibles", example = "5")
        Integer stock,
        
        @PastOrPresent(message = "La fecha de publicación no puede ser futura")
        LocalDate fechaPublicacion,

        @Min(0)
        @Max(1)
        @Schema(description = "Estado del registro (1: Activo, 0: Inactivo)", example = "1")
        Integer estado
) {
    
}
