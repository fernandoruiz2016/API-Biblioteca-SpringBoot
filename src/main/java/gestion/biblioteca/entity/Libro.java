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
