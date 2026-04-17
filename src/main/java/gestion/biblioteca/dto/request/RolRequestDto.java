package gestion.biblioteca.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

@Schema(description= "DTO para las solicitudes de Roles")
public record RolRequestDto(
        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 50, message = "El nombre no puede exceder los 50 caracteres")
        @Schema(description = "Nombre único del rol", example = "ROLE_ADMIN")
        String nombre,

        @Size(max = 150, message = "La descripción no puede exceder los 150 caracteres")
        @Schema(description = "Explicación breve de las funciones del rol", example = "Administrador con acceso total al sistema")
        String descripcion,

        @Min(0)
        @Max(1)
        @Schema(description = "Estado del rol (1: Activo, 0: Inactivo)", example = "1")
        Integer estado
) {}
