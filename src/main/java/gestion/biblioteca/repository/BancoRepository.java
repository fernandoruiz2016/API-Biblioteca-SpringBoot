package gestion.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import gestion.biblioteca.entity.Banco;

@Repository
public interface BancoRepository extends JpaRepository<Banco,Long> {

}
