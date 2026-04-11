package gestion.biblioteca.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RolRequestDto {
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 50, message = "El nombre no puede exceder los 50 caracteres")
    private String nombre;

    @Size(max = 150, message = "La descripción no puede exceder los 150 caracteres")
    private String descripcion;

    @NotNull(message = "El estado es obligatorio")
    @Min(0)
    @Max(1)
    private Integer estado;
}
