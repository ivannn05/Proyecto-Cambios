package api.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import api.Daos.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    
    // Encuentra un producto por su ID
    Optional<Producto> findById(Long productoId);
}
