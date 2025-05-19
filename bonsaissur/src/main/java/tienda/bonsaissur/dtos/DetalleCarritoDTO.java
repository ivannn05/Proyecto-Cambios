package tienda.bonsaissur.dtos;

import java.math.BigDecimal;

public class DetalleCarritoDTO {
	 private String nombre;
	    private BigDecimal precio;
	    private String imagen;
	    private int cantidad;
	    private Long subtotal;

	    
	    
	    public DetalleCarritoDTO() {
			super();
		}

		public DetalleCarritoDTO(String nombre, BigDecimal precio, String imagen, int cantidad, Long subtotal) {
			super();
			this.nombre = nombre;
			this.precio = precio;
			this.imagen = imagen;
			this.cantidad = cantidad;
			this.subtotal = subtotal;
		}

		// Getters y Setters
	    public String getNombre() {
	        return nombre;
	    }

	    public void setNombre(String nombre) {
	        this.nombre = nombre;
	    }

	    public BigDecimal getPrecio() {
	        return precio;
	    }

	    public void setPrecio(BigDecimal precio) {
	        this.precio = precio;
	    }

	    public String getImagen() {
	        return imagen;
	    }

	    public void setImagen(String imagen) {
	        this.imagen = imagen;
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
