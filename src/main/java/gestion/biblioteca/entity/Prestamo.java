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

        String username = getAuthenticatedUser();
        this.usuarioCreacion = username;

        if (this.estado == null) {
            this.estado = 1;
        }

        if (this.ipCreacion == null) {
            this.ipCreacion = "127.0.0.1";
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.fechaModificacion = LocalDateTime.now();
        this.usuarioModificacion = getAuthenticatedUser();

        if (this.ipModificacion == null) {
            this.ipModificacion = "127.0.0.1";
        }
    }

    private String getAuthenticatedUser() {
        org.springframework.security.core.Authentication authentication =
                org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated() &&
                !(authentication instanceof org.springframework.security.authentication.AnonymousAuthenticationToken)) {
            return authentication.getName();
        }

        return "SYSTEM_ADMIN"; // Usuario por defecto
    }
}
