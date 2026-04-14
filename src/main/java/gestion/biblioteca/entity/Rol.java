package gestion.biblioteca.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table (name = "tm_rol")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tm_rol")
    @SequenceGenerator(name = "seq_tm_rol", sequenceName = "seq_tm_rol", allocationSize = 1)
    @Column(name = "nidrol")
    private Long idRol;

    @Column(name = "snombre", nullable = false, length = 50, unique = true)
    private String nombre;

    @Column(name = "sdescripcion", length = 150)
    private String descripcion;

    @Column(name = "nestado", nullable = false)
    private Integer estado;

    @Column(name = "susuariocreacion", nullable = false, length = 50, updatable = false)
    private String usuarioCreacion;

    @Column(name = "susuariomodificacion", length = 50)
    private String usuarioModificacion;

    @Column(name = "dfechacreacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "dfechamodificacion")
    private LocalDateTime fechaModificacion;

    @Column(name = "sipcreacion", length = 20, updatable = false)
    private String ipCreacion;

    @Column(name = "sipmodificacion", length = 20)
    private String ipModificacion;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<Usuario> usuarios;

    @PrePersist
    protected void onCreate() {
        this.fechaCreacion = LocalDateTime.now();

        if (this.estado == null) {
            this.estado = 1;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.fechaModificacion = LocalDateTime.now();
    }
}
