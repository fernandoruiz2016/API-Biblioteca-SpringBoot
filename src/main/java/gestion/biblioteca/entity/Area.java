package gestion.biblioteca.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table (name = "tm_area")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tm_area")
    @SequenceGenerator(name = "seq_tm_area", sequenceName = "seq_tm_area", allocationSize = 1)
    @Column(name = "nidarea")
    private Long idArea;

    @Column(name = "snombre", nullable = false, length = 30)
    private String nombre;

    @Column(name = "sdescripcion", nullable = false)
    private String descripcion;

    @Column(name = "nestado")
    private Integer estado;

    @OneToMany(mappedBy = "area", fetch = FetchType.LAZY)
    private List<Empleado> empleados;
}

