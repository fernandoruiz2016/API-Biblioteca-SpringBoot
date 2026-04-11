package gestion.biblioteca.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table (name = "tm_prestamo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tm_prestamo")
    @SequenceGenerator(name = "seq_tm_prestamo", sequenceName = "seq_tm_prestamo", allocationSize = 1)
    @Column(name = "nidprestamo")
    private Long idPrestamo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nidlibro", nullable = false)
    private Libro libro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nidusuario", nullable = false)
    private Usuario usuario;

    @Column(name = "dfechaprestamo", nullable = false)
    private LocalDateTime fechaPrestamo;

    @Column(name = "ddevolucionesperada", nullable = false)
    private LocalDateTime fechaDevolucionEsperada;

    @Column(name = "ddevolucionreal")
    private LocalDateTime fechaDevolucionReal;

    @Column(name = "nestado", nullable = false)
    private Integer estado; // 1: Activo, 2: Devuelto, 3: Moroso

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
