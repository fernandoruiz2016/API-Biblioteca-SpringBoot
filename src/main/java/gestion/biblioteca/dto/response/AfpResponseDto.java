package gestion.biblioteca.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AfpResponseDto {
    private Long idAfp;
    private String nombre;
    private BigDecimal comisionFija;
    private BigDecimal comisionVariable;
    private BigDecimal rendimiento;
    private Integer estado;
}
