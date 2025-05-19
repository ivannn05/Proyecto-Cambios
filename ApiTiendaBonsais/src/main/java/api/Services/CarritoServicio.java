package api.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.Daos.Carrito;
import api.Daos.CarritoDetalle;
import api.Daos.DetalleCarritoDTO;
import api.Daos.Producto;
import api.Repository.CarritoDetalleRepository;
import api.Repository.CarritoRepository;
import api.Repository.ProductoRepository;
import api.Utilidades.Util;
import jakarta.transaction.Transactional;

@Service
public class CarritoServicio {

    private final CarritoRepository carritoRepository;
    private final CarritoDetalleRepository carritoDetalleRepository;
    private final ProductoRepository productoRepository;

    @Autowired
    public CarritoServicio(CarritoRepository carritoRepository, CarritoDetalleRepository carritoDetalleRepository, ProductoRepository productoRepository) {
        this.carritoRepository = carritoRepository;
        this.carritoDetalleRepository = carritoDetalleRepository;
        this.productoRepository = productoRepository;
    }

    // Método para agregar un producto al carrito
    public String agregarProducto(Long usuarioId, Long productoId, int cantidad) {
        try {
            // Obtener el carrito del usuario
            Carrito carrito = carritoRepository.findByUsuarioId(usuarioId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

            // Obtener el producto
            Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            // Verificar si ya existe el producto en el carrito
            Optional<CarritoDetalle> existente = carritoDetalleRepository
                .findByCarritoIdCarritoAndProductoIdProducto(carrito.getIdCarrito(), productoId);

            if (existente.isPresent()) {
                CarritoDetalle detalle = existente.get();
                detalle.setCantidad(detalle.getCantidad() + cantidad);
                carritoDetalleRepository.save(detalle);
            } else {
                CarritoDetalle nuevo = new CarritoDetalle();
                nuevo.setCarrito(carrito);
                nuevo.setProducto(producto);
                nuevo.setCantidad(cantidad);
                carritoDetalleRepository.save(nuevo);
            }

            return "Producto agregado al carrito con éxito";
        } catch (Exception e) {
            Util.ficheroLog("Ocurrio un error en agregarProducto: " + e.getMessage());
            return "Error al agregar producto al carrito";
        }
    }

    // Método para obtener el carrito de un usuario
    public Carrito obtenerCarrito(Long usuarioId) {
        try {
            return carritoRepository.findByUsuarioId(usuarioId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));
        } catch (Exception e) {
            Util.ficheroLog("Ocurrio un error en obtenerCarrito: " + e.getMessage());
            return null;
        }
    }
    public List<DetalleCarritoDTO> obtenerDetallesDelCarrito(Long usuarioId) {
        Carrito carrito = carritoRepository.findByUsuarioId(usuarioId)
            .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

        List<CarritoDetalle> detalles = carritoDetalleRepository.findByCarrito(carrito);

        return detalles.stream().map(detalle -> {
            Producto producto = detalle.getProducto();
            DetalleCarritoDTO dto = new DetalleCarritoDTO();
            dto.setNombre(producto.getNombre());
            dto.setPrecio(producto.getPrecio());
            dto.setImagen(producto.getImagenUrl());
            dto.setCantidad(detalle.getCantidad());
            dto.setSubtotal(detalle.getSubtotal());
            return dto;
        }).collect(Collectors.toList());
    }



    // Método para eliminar un producto del carrito
    @Transactional
    public String eliminarProducto(Long usuarioId, Long productoId) {
        try {
            Carrito carrito = carritoRepository.findByUsuarioId(usuarioId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

            CarritoDetalle detalle = carritoDetalleRepository.findByCarritoIdCarritoAndProductoIdProducto(carrito.getIdCarrito(), productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado en el carrito"));

            carritoDetalleRepository.delete(detalle);
            return "Producto eliminado del carrito con éxito";
        } catch (Exception e) {
            Util.ficheroLog("Ocurrio un error en eliminarProducto: " + e.getMessage());
            return "Error al eliminar producto del carrito";
        }
    }
}
