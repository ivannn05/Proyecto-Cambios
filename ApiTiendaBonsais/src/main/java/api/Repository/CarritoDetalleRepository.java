package api.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import api.Daos.Carrito;
import api.Daos.CarritoDetalle;

public interface CarritoDetalleRepository extends JpaRepository<CarritoDetalle, Long> {
    
    // Encuentra un detalle de carrito por el ID del carrito y el ID del producto
	Optional<CarritoDetalle> findByCarritoIdCarritoAndProductoIdProducto(Long idCarrito, Long idProducto);
	List<CarritoDetalle> findByCarrito_IdCarrito(Long idCarrito);
    List<CarritoDetalle> findByCarrito(Carrito carrito);
    // Elimina un detalle de carrito
    void delete(CarritoDetalle carritoDetalle);
}