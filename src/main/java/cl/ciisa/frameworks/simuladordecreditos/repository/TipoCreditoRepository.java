package cl.ciisa.frameworks.simuladordecreditos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.ciisa.frameworks.simuladordecreditos.models.TipoCredito;

@Repository
public interface TipoCreditoRepository extends JpaRepository<TipoCredito, Long> {

}
