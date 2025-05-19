package tienda.bonsaissur.dtos;

import java.util.List;

public class Pedido {
    private Long pedidoId;
    private Long usuarioId;
    private List<DetallePedido> detalles;
    private Long total;

    public Pedido() {
        super();
    }

    public Pedido(Long pedidoId, Long usuarioId, List<DetallePedido> detalles, Long total) {
        super();
        this.pedidoId = pedidoId;
        this.usuarioId = usuarioId;
        this.detalles = detalles;
        this.total = total;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public List<DetallePedido> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallePedido> detalles) {
        this.detalles = detalles;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
