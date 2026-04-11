package gestion.biblioteca.repository;

import gestion.biblioteca.entity.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PrestamoRepository  extends JpaRepository<Prestamo,Long> {
    @Query("SELECT p FROM Prestamo p WHERE UPPER(p.libro.titulo) LIKE UPPER(CONCAT('%', :titulo, '%'))")
    List<Prestamo> findByLibroTitulo(@Param("titulo") String titulo);

    @Query("SELECT p FROM Prestamo p WHERE UPPER(p.usuario.apellido) LIKE UPPER(CONCAT('%', :apellido, '%'))")
    List<Prestamo> findByUsuarioApellido(@Param("apellido") String apellido);

    List<Prestamo> findByUsuario_IdUsuario(Long idUsuario);

    List<Prestamo> findByLibro_IdLibro(Long idLibro);

    List<Prestamo> findByEstado(Integer estado);
}
