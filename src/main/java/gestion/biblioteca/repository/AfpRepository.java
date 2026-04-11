package gestion.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import gestion.biblioteca.entity.Afp;

import java.util.List;

@Repository
public interface AfpRepository extends JpaRepository<Afp,Long> {
    List<Afp> findByEstado(Integer estado);
}
