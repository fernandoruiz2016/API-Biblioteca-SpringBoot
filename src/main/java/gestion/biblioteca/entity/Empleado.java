package gestion.biblioteca.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table (name = "tm_empleado")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tm_empleado")
    @SequenceGenerator(name = "seq_tm_empleado", sequenceName = "seq_tm_empleado", allocationSize = 1)
    @Column(name = "nidempleado")
    private Long idEmpleado;

    @Column(name = "sdni", nullable = false, unique = true, length = 8)
    private String dni;

    @Column(name = "snombres", nullable = false, length = 50)
    private String nombres;

    @Column(name = "sapellidopaterno", nullable = false, length = 30)
    private String apellidoPaterno;

    @Column(name = "sapellidomaterno", nullable = false, length = 30)
    private String apellidoMaterno;

    @Column(name = "nsalario", nullable = false, precision = 20, scale = 2)
    private BigDecimal salario;

    @Column(name = "snacionalidad")
    private String nacionalidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAfp", nullable = false)
    private Afp afp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idArea", nullable = false)
    private Area area;

}
