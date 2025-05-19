package tienda.bonsaissur.dtos;

import java.math.BigDecimal;

public class DetallePedido {
    private Long productoId;
    private String nombre;
    private int cantidad;
    private BigDecimal subtotal;  // Este campo es el que se calcula, no el precio

    public DetallePedido() {
        super();
    }

    public DetallePedido(Long productoId, String nombre, int cantidad, BigDecimal subtotal) {
        super();
        this.productoId = productoId;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

}
