package api.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.Services.PedidoServicio;

@RestController
@RequestMapping("/api/pedido")
@CrossOrigin(origins = "*")
public class PedidoControlador {

    @Autowired
    private PedidoServicio pedidoServicio;

    @PostMapping("/confirmar/{usuarioId}")
    public ResponseEntity<String> confirmarPedido(@PathVariable Long usuarioId) {
        String resultado = pedidoServicio.confirmarPedido(usuarioId);
        if (resultado.startsWith("Pedido confirmado")) {
            return ResponseEntity.ok(resultado);
        } else {
            return ResponseEntity.status(400).body(resultado);
        }
    }
}
