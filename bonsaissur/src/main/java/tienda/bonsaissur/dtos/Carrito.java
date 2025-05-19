package tienda.bonsaissur.dtos;

import java.util.List;

public class Carrito {
    private Long carritoId;
    private Long usuarioId;
    private List<ProductoCarrito> productos;

    public Carrito() {
        super();
    }

    public Carrito(Long carritoId, Long usuarioId, List<ProductoCarrito> productos) {
        super();
        this.carritoId = carritoId;
        this.usuarioId = usuarioId;
        this.productos = productos;
    }

    public Long getCarritoId() {
        return carritoId;
    }

    public void setCarritoId(Long carritoId) {
        this.carritoId = carritoId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public List<ProductoCarrito> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoCarrito> productos) {
        this.productos = productos;
    }
}
