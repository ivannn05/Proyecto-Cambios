package tienda.bonsaissur.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import tienda.bonsaissur.dtos.Usuario;
import tienda.bonsaissur.services.CarritoServicio;

@Controller
public class CarritoControlador {

    private final CarritoServicio carritoServicio;

    @Autowired
    public CarritoControlador(CarritoServicio carritoServicio) {
        this.carritoServicio = carritoServicio;
    }

    @Controller
    public class CarritoController {

        @GetMapping("/carrito")
        public ResponseEntity<Void> mostrarCarrito(HttpSession session) {
            // Obtener el usuario de la sesión
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            
            // Si no hay usuario en sesión, redirigir a login
            if (usuario == null) {
                return ResponseEntity.status(HttpStatus.FOUND) // Código de estado 302 (Found)
                                     .location(URI.create("/login")) // Redirige a login
                                     .build();
            }

            // Si el usuario está logueado, redirige a la página del carrito
            return ResponseEntity.status(HttpStatus.FOUND) // Código de estado 302 (Found)
                                 .location(URI.create("/carrito.jsp")) // Redirige al carrito
                                 .build();
        }

        @PostMapping("/carrito")
        public ResponseEntity<Void> agregarAlCarrito(@RequestParam Long productoId,
                                                     @RequestHeader(value = "Referer", required = false) String referer,
                                                     HttpSession session) {
            // Verificar que el usuario esté logueado
            Usuario usuario = (Usuario) session.getAttribute("usuario");

            if (usuario == null) {
                return ResponseEntity.status(HttpStatus.FOUND) // Si no está logueado, redirigir a login
                                     .location(URI.create("/login"))
                                     .build();
            }

            Long idUsuario = usuario.getId();
            int cantidad = 1;

            // Llamar al servicio para agregar el producto al carrito
            carritoServicio.agregarProductoCarrito(idUsuario, productoId, cantidad);

            // Redirigir al carrito o a la página anterior (usando referer si existe)
            return ResponseEntity.status(HttpStatus.FOUND)
                                 .location(URI.create(referer != null ? referer : "/carrito"))
                                 .build();
        }
    }


}
