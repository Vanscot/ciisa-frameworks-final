package cl.ciisa.frameworks.simuladordecreditos.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.ciisa.frameworks.simuladordecreditos.models.Credito;

@Repository
public interface CreditoRepository extends JpaRepository<Credito, Long> {

}