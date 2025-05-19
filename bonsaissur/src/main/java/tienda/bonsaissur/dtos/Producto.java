package tienda.bonsaissur.dtos;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Clase DTO encargada de contener los campos del Producto
 */
public class Producto {

	private Long idProducto;
	private String nombre;
	private String categoria;
	private BigDecimal precio;
	private Integer stock;
	private String imagenUrl;

	// Constructor vacío
	public Producto() {
	}

	// Constructor con parámetros
	public Producto(Long idProducto, String nombre, String categoria, BigDecimal precio, Integer stock,
			String imagenUrl) {
		this.idProducto = idProducto;
		this.nombre = nombre;
		this.categoria = categoria;
		this.precio = precio;
		this.stock = stock;
		this.imagenUrl = imagenUrl;
	
	}

	// Getters y Setters
	public Long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getImagenUrl() {
		return imagenUrl;
	}

	public void setImagenUrl(String imagenUrl) {
		this.imagenUrl = imagenUrl;
	}

	

	@Override
	public String toString() {
		return "Producto{" + "idProducto=" + idProducto + ", nombre='" + nombre + '\'' + ", categoria='" + categoria
				+ '\'' +

				", precio=" + precio + ", stock=" + stock + ", imagenUrl='" + imagenUrl + '\'' +  '}';
	}
}
