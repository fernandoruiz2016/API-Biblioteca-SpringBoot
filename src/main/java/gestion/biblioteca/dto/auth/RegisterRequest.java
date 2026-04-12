package gestion.biblioteca.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Set;

@Schema(description = "Clase (record) que permite registrar un nuevo usuario")
public record RegisterRequest(
        @NotBlank(message = "El username es obligatorio")
        @Size(min = 8, max = 30, message = "El usuario debe tener como mínimo 8 y 30 caracteres")
        @Schema(example = "jperez")
        String username,

        @NotBlank(message = "El password es obligatorio")
        @Size(min = 6, max = 20, message = "El password debe tener como mínimo 6 y 20 caracteres")
        @Schema(example = "123456")
        String password,

        @Schema(description = "Roles: ADMIN | USER | RRHH")
        Set<String> roles
) {
}

