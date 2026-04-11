package gestion.biblioteca.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioRequestDto {
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede exceder los 100 caracteres")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(max = 100, message = "El apellido no puede exceder los 100 caracteres")
    private String apellido;

    @NotBlank(message = "El correo electrónico es obligatorio")
    @Email(message = "Debe proporcionar un formato de correo válido")
    @Size(max = 150, message = "El correo no puede exceder los 150 caracteres")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, max = 20, message = "La contraseña debe tener entre 6 y 20 caracteres")
    private String password;

    @NotNull(message = "El estado es obligatorio")
    @Min(0)
    @Max(1)
    private Integer estado;

    @NotNull(message = "Debe asignar un rol al usuario")
    private Long idRol;


}
