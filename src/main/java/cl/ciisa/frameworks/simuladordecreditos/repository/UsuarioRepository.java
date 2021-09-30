package cl.ciisa.frameworks.simuladordecreditos.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.ciisa.frameworks.simuladordecreditos.models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	List<Usuario> findByRut( Long rut );
}
 