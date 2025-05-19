package api.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import api.Daos.Carrito;
import api.Daos.CarritoDetalle;
import api.Daos.DetalleCarritoDTO;
import api.Services.CarritoServicio;

@RestController
@RequestMapping("/api/carrito")
@CrossOrigin(origins = "*")
public class CarritoControlador {

    @Autowired
    private CarritoServicio carritoServicio;

    // ✅ Agregar producto al carrito
    @PostMapping("/agregar")
    public ResponseEntity<String> agregarAlCarrito(@RequestBody CarritoDetalle carro) {
        try {
            Long idUsuario = carro.getCarrito().getUsuario().getId();
            Long idProducto = carro.getProducto().getIdProducto();
            int cantidad = carro.getCantidad();

            String mensaje = carritoServicio.agregarProducto(idUsuario, idProducto, cantidad);
            return ResponseEntity.ok(mensaje);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al añadir al carrito: " + e.getMessage());
        }
    }

    // ✅ Obtener carrito del usuario (solo devuelve el carrito sin detalles)
    @GetMapping("/{idUsuario}")
    public ResponseEntity<Carrito> obtenerCarrito(@PathVariable Long idUsuario) {
        try {
            Carrito carrito = carritoServicio.obtenerCarrito(idUsuario);
            if (carrito == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(carrito);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
    
    @GetMapping("/{idUsuario}/detalles")
    public ResponseEntity<List<DetalleCarritoDTO>> obtenerDetallesCarrito(@PathVariable Long idUsuario) {
        try {
            List<DetalleCarritoDTO> detalles = carritoServicio.obtenerDetallesDelCarrito(idUsuario);
            return ResponseEntity.ok(detalles);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



    // ✅ Eliminar producto del carrito
    @DeleteMapping("/eliminar")
    public ResponseEntity<String> eliminarProducto(@RequestParam Long idUsuario,
                                                   @RequestParam Long idProducto) {
        try {
            String mensaje = carritoServicio.eliminarProducto(idUsuario, idProducto);
            return ResponseEntity.ok(mensaje);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al eliminar producto: " + e.getMessage());
        }
    }
}
