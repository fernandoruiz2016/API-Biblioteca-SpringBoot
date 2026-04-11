package gestion.biblioteca.dto.response;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BancoResponseDto {
    private Long idBanco;
    private String nombre;
    private String numeroCuenta;
    private Integer estado;
}
