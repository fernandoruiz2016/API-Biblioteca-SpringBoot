package gestion.biblioteca.repository;

import gestion.biblioteca.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RolRepository extends JpaRepository<Rol,Long> {
    @Query("SELECT r FROM Rol r WHERE UPPER(r.nombre) LIKE UPPER(CONCAT('%', :nombre, '%'))")
    List<Rol> findByNombre(@Param("nombre") String nombre);

    List<Rol> findByEstado(Integer estado);
}
