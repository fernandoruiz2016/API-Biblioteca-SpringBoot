package gestion.biblioteca.repository;

import gestion.biblioteca.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro,Long> {
    @Query("SELECT l FROM Libro l WHERE UPPER(l.titulo) LIKE UPPER(CONCAT('%', :titulo, '%'))")
    List<Libro> findByTitulo(@Param("titulo") String titulo);

    List<Libro> findByEstado(Integer estado);

    List<Libro> findByAutorContainingIgnoreCase(String autor);
}
