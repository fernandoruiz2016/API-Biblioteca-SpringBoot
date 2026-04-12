package gestion.biblioteca.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Set;

@Schema(description = "Información del login con el token JWT")
public record LoginResponse(
        @Schema(description = "Token JWT Bearer")
        String token,

        @Schema(description = "Bearer")
        String tipo,

        @Schema(description = "jperez")
        String username,

        @Schema(description = "admin, user")
        Set<String> roles,

        @Schema(description = "Tiempo de expiración en milisegundos", example = "86400000")
        long expiracionms
) {
}
