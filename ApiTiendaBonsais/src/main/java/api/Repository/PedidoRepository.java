package api.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import api.Daos.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    // No es necesario agregar métodos adicionales, JpaRepository se encarga de las operaciones CRUD básicas.
}
