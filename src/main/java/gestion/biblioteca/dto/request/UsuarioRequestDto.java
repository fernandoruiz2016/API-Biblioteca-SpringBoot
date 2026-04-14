package gestion.biblioteca.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.util.Set;

@Schema(description= "DTO para las solicitudes de Usuarios")
public record UsuarioRequestDto(
        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 20, message = "El nombre no puede exceder los 20 caracteres")
        @Schema(description = "Nombre de usuario único para el acceso al sistema", example = "jperez")
        String username,

        @NotBlank(message = "El correo electrónico es obligatorio")
        @Email(message = "Debe proporcionar un formato de correo válido")
        @Size(max = 30, message = "El correo no puede exceder los 30 caracteres")
        @Schema(description = "Correo electrónico", example = "example@email.com")
        String email,
        
        @NotBlank(message = "La contraseña es obligatoria")
        @Size(min = 6, max = 20, message = "La contraseña debe tener entre 6 y 20 caracteres")
        @Schema(description = "Contraseña de acceso", example = "xxxxxx")
        String password,
        
        @NotNull(message = "El estado es obligatorio")
        @Min(0)
        @Max(1)
        @Schema(description = "Estado de la cuenta (1: Activo, 0: Suspendido/Inactivo)", example = "1")
        Integer estado,
        
        @NotNull(message = "Debe asignar un rol al usuario")
        @Schema(description = "Roles que se asignarán al usuario", example = "ADMIN")
        Set<String> roles
) {}
