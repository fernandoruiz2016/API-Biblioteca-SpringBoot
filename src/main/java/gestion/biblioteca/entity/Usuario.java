package gestion.biblioteca.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table (name = "tm_usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tm_usuario")
    @SequenceGenerator(name = "seq_tm_usuario", sequenceName = "seq_tm_usuario", allocationSize = 1)
    @Column(name = "nidusuario")
    private Long idUsuario;

    @Column(name = "snombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "sapellido", nullable = false, length = 100)
    private String apellido;

    @Column(name = "semail", nullable = false, unique = true, length = 150)
    private String email;

    @Column(name = "spassword", nullable = false, length = 255)
    private String password;

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

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<Prestamo> prestamos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nidrol", nullable = false)
    private Rol rol;

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
