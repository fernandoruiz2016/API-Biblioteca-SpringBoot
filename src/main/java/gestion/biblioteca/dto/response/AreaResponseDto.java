package gestion.biblioteca.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AreaResponseDto {
    private Long idArea;
    private String nombre;
    private String descripcion;
    private Integer estado;
}
