package gestion.biblioteca.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmpleadoRequestDto {

    @NotBlank(message = "El dni es obligatorio")
    @Size(max = 8, message = "El dni debe ser de 8 dígitos")
    private String dni;

    private String nombres;

    private String apellidoPaterno;

    private String apellidoMaterno;

    private BigDecimal salario;

    private String nacionalidad;

    private Long idAfp;

    private long idArea;
}
