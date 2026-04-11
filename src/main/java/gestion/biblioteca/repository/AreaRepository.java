package gestion.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import gestion.biblioteca.entity.Area;

@Repository
public interface AreaRepository extends JpaRepository<Area, Long> {

}
