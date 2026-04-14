package gestion.biblioteca.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Column(name = "susername", nullable = false, unique = true, length = 20)
    private String username;

    @Column(name = "semail", nullable = false, unique = true, length = 30)
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "tt_usuario_rol",
            joinColumns = @JoinColumn(name = "nidusuario"),
            inverseJoinColumns = @JoinColumn(name = "nidrol")
    )

    @Builder.Default
    private Set<Rol> roles = new HashSet<>();

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
