package tienda.bonsaissur.services;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import tienda.bonsaissur.util.util;
@Service
public class CarritoServicio {
	public String agregarProductoCarrito(Long idUsuario, Long idProducto, int cantidad) {
	    try {
	        HttpClient client = HttpClient.newHttpClient();
	        ObjectMapper objectMapper = new ObjectMapper();
	        String API_URL = "https://mitienda.iloposa.eu:8081/api/carrito/agregar";

	        // Crear el objeto DTO
	        Map<String, Object> requestBody = new HashMap<>();
	        requestBody.put("idUsuario", idUsuario);
	        requestBody.put("idProducto", idProducto);
	        requestBody.put("cantidad", cantidad);

	        String json = objectMapper.writeValueAsString(requestBody);

	        HttpRequest request = HttpRequest.newBuilder()
	                .uri(URI.create(API_URL))
	                .header("Content-Type", "application/json")
	                .POST(HttpRequest.BodyPublishers.ofString(json))
	                .build();

	        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

	        System.out.println("Respuesta del servidor: " + response.body());
	        return "Producto añadido al carrito";

	    } catch (Exception e) {
	        util.ficheroLog("Error al añadir producto al carrito: " + e.getMessage());
	        return "Error al añadir al carrito";
	    }
	}

}
