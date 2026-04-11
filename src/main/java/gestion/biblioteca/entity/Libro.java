package gestion.biblioteca.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table (name = "tm_libro")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tm_libro")
    @SequenceGenerator(name = "seq_tm_libro", sequenceName = "seq_tm_libro", allocationSize = 1)
    @Column(name = "nidlibro")
    private Long idLibro;

    @Column(name = "stitulo", nullable = false, length = 150)
    private String titulo;

    @Column(name = "sautor", nullable = false, length = 100)
    private String autor;

    @Column(name = "nstock", nullable = false)
    private Integer stock;

    @Column(name = "dfechapublicacion")
    private LocalDate fechaPublicacion;

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

    @OneToMany(mappedBy = "libro", fetch = FetchType.LAZY)
    private List<Prestamo> prestamos;

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
