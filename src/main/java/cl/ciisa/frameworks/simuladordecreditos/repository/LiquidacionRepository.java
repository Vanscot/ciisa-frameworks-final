package cl.ciisa.frameworks.simuladordecreditos.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.ciisa.frameworks.simuladordecreditos.models.Liquidacion;
@Repository
public interface LiquidacionRepository extends JpaRepository<Liquidacion, Long> {
	public Optional<List<Liquidacion>> findByRut( long rut );
}
