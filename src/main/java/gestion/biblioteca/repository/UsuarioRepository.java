package gestion.biblioteca.repository;

import gestion.biblioteca.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
/*
    @Query("SELECT u FROM Usuario u WHERE UPPER(u.apellido) LIKE UPPER(CONCAT('%', :apellido, '%'))")
    List<Usuario> findByApellido(@Param("apellido") String apellido);
*/

    Optional<Usuario> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

}
