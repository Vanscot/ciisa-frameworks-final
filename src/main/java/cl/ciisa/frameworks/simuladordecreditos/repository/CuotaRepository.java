package cl.ciisa.frameworks.simuladordecreditos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.ciisa.frameworks.simuladordecreditos.models.Cuota;

@Repository
public interface CuotaRepository extends JpaRepository<Cuota, Long> {
	List<Cuota> getByCredito( String nombre );
}
