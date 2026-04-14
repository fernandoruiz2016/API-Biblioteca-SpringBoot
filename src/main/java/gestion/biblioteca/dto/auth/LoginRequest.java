package gestion.biblioteca.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description= "Credenciales de acceso")
public record LoginRequest(
        @NotBlank(message = "El usuario es obligatorio")
        @Schema(example = "admin")
        String username,

        @NotBlank(message = "La clave es obligatoria")
        @Size(min = 6, max = 20, message = "La contraseña debe tener entre 6 y 20 caracteres")
        @Schema(example = "xxxxxx")
        String password
) {}

