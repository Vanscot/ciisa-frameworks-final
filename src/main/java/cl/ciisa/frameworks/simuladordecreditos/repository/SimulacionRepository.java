package cl.ciisa.frameworks.simuladordecreditos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.ciisa.frameworks.simuladordecreditos.models.Simulacion;

@Repository
public interface SimulacionRepository extends JpaRepository<Simulacion, Long> {

}
