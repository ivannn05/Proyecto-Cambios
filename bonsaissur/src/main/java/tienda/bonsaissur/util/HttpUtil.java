package tienda.bonsaissur.util;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import tienda.bonsaissur.dtos.DetalleCarritoDTO;

public class HttpUtil {
    public static List<DetalleCarritoDTO> obtenerDetallesCarrito(Long idUsuario) throws Exception {
    	URL url = new URL("https://api.mitienda.iloposa.eu/api/carrito/" + idUsuario + "/detalles");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        if (conn.getResponseCode() == 200) {
            InputStream is = conn.getInputStream();
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(is, new TypeReference<List<DetalleCarritoDTO>>() {});
        } else {
            throw new RuntimeException("Error al obtener el carrito: Código " + conn.getResponseCode());
        }
    }
}