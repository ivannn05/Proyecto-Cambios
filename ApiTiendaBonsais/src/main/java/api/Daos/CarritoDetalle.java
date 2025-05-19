package api.Daos;

import jakarta.persistence.*;

@Entity
@Table(name = "carrito_detalles", schema = "tiendabonsai")
public class CarritoDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle")  // Nombre de la columna para la clave primaria
    private Long idDetalle;

    @ManyToOne
    @JoinColumn(name = "carrito_id", nullable = false)  // Relación con el carrito
    private Carrito carrito;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)  // Relación con el producto
    private Producto producto;

    @Column(name = "cantidad", nullable = false)  // Cantidad de productos
    private int cantidad;

    @Column(name = "subtotal", nullable = false)  // Subtotal calculado (precio * cantidad)
    private Long subtotal;

    // Getters y Setters
    public Long getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Long idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Long getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Long subtotal) {
        this.subtotal = subtotal;
    }
}
