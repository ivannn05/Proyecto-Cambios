package tienda.bonsaissur.dtos;

public class ProductoCarrito {
    private Long productoId;
    private String nombre;
    private Long precio;
    private int cantidad;

    public ProductoCarrito() {
        super();
    }

    public ProductoCarrito(Long productoId, String nombre, Long precio, int cantidad) {
        super();
        this.productoId = productoId;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
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

    public Long getPrecio() {
        return precio;
    }

    public void setPrecio(Long precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
