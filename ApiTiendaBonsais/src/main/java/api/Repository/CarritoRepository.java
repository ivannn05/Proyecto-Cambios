package api.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import api.Daos.Carrito;
import api.Daos.CarritoDetalle;

public interface CarritoRepository extends JpaRepository<Carrito, Long> {
	
	
    Optional<Carrito> findByUsuarioId(Long usuarioId);
}
