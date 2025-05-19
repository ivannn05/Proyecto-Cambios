package api.Services;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.Daos.*;
import api.Repository.*;
import api.Utilidades.Util;
import jakarta.transaction.Transactional;

@Service
public class PedidoServicio {

    @Autowired
    private PedidoRepository pedidoRepository;


    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private CarritoDetalleRepository carritoDetalleRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public String confirmarPedido(Long usuarioId) {
        try {
            Usuario usuario = usuarioRepository.findById(usuarioId)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            Carrito carrito = carritoRepository.findByUsuarioId(usuarioId)
                    .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

            List<CarritoDetalle> detallesCarrito = carritoDetalleRepository.findByCarrito_IdCarrito(carrito.getIdCarrito());

            if (detallesCarrito.isEmpty()) {
                return "El carrito está vacío";
            }

            Pedido pedido = new Pedido();
            pedido.setUsuario(usuario);

            List<DetallePedido> detallesPedido = detallesCarrito.stream().map(cd -> {
                DetallePedido dp = new DetallePedido();
                dp.setProducto(cd.getProducto());
                dp.setCantidad(cd.getCantidad());

                // Conversión de cantidad a BigDecimal para multiplicar con BigDecimal precio
                BigDecimal cantidadDecimal = new BigDecimal(cd.getCantidad());
                BigDecimal subtotal = cd.getProducto().getPrecio().multiply(cantidadDecimal);

                dp.setSubtotal(subtotal.longValue()); // Convertimos el resultado de BigDecimal a long
                dp.setPedido(pedido);
                return dp;
            }).collect(Collectors.toList());

            // Calculando el total utilizando BigDecimal para mayor precisión
            BigDecimal total = detallesPedido.stream()
                    .map(dp -> new BigDecimal(dp.getSubtotal()))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            pedido.setTotal(total.longValue()); // Convertimos a long para almacenarlo en el DTO
            pedido.setDetalles(detallesPedido);

            pedidoRepository.save(pedido); // Guarda todo gracias al CascadeType.ALL

            carritoDetalleRepository.deleteAll(detallesCarrito); // Limpia el carrito

            return "Pedido confirmado correctamente";
        } catch (Exception e) {
            Util.ficheroLog("Error al confirmar pedido: " + e.getMessage());
            return "Error al confirmar el pedido";
        }
    }
}
