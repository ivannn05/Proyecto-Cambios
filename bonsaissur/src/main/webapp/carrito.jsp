<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="tienda.bonsaissur.dtos.Usuario"%>
<%@page import="tienda.bonsaissur.dtos.DetallePedido"%>
<%@ page import="java.util.List" %>
<%@ page import="java.math.BigDecimal" %>


<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    List<DetallePedido> listaCarrito = (List<DetallePedido>) session.getAttribute("listaCarrito");
    double totals = 0;
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Carrito - Bonsái Sur</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<!-- CABECERA -->
<nav class="navbar navbar-expand-lg navbar-dark bg-success">
    <div class="container-fluid">
        <a class="navbar-brand" href="index.jsp">🌿 Bonsái Sur</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" 
                data-bs-target="#navbarNav" aria-controls="navbarNav" 
                aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav me-auto">
                <li class="nav-item"><a class="nav-link" href="productos.jsp">Productos</a></li>
                <li class="nav-item"><a class="nav-link active" href="carrito.jsp">Carrito</a></li>
                <li class="nav-item"><a class="nav-link" href="misPedidos.jsp">Mis pedidos</a></li>
            </ul>
            <ul class="navbar-nav">
                <% if (usuario != null) { %>
                    <li class="nav-item">
                        <span class="navbar-text text-white me-3">
                            Bienvenido, <%= usuario.getNombre() %>
                        </span>
                    </li>
                    <li class="nav-item"><a class="nav-link" href="logout.jsp">Cerrar sesión</a></li>
                <% } else { %>
                    <li class="nav-item"><a class="nav-link" href="login.jsp">Iniciar sesión</a></li>
                <% } %>
            </ul>
        </div>
    </div>
</nav>

<!-- CONTENIDO DEL CARRITO -->
<%@ page import="java.math.BigDecimal" %>


<div class="container mt-4">
    <h2>Carrito de Compras</h2>

    <% 
    // Inicializa el total como BigDecimal antes del bucle
    BigDecimal total = BigDecimal.ZERO; 
    %>

    <% if (listaCarrito != null && !listaCarrito.isEmpty()) { %>
        <table class="table table-bordered table-hover mt-4">
            <thead class="table-success">
                <tr>
                    <th>Producto</th>
                    <th>Precio</th>
                    <th>Cantidad</th>
                    <th>Subtotal</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <% 
                // Recorremos la lista de productos en el carrito
                for (DetallePedido producto : listaCarrito) {
                    BigDecimal subtotal = producto.getSubtotal(); 
                    total = total.add(subtotal); // Acumulamos el total
                %>
                    <tr>
                        <td><%= producto.getNombre() %></td>
                        <td>$<%= producto.getSubtotal().setScale(2, BigDecimal.ROUND_HALF_EVEN) %></td> <!-- Muestra el subtotal formateado -->
                        <td><%= producto.getCantidad() %></td>
                        <td>$<%= subtotal.setScale(2, BigDecimal.ROUND_HALF_EVEN) %></td> <!-- Muestra el subtotal formateado -->
                        <td>
                            <!-- Formulario para eliminar producto del carrito -->
                            <form action="eliminarDelCarrito" method="post" class="d-inline">
                                <input type="hidden" name="productoId" value="<%= producto.getProductoId() %>">
                                <button type="submit" class="btn btn-danger btn-sm">Eliminar</button>
                            </form>
                        </td>
                    </tr>
                <% } %>
            </tbody>
        </table>

        <div class="text-end">
            <h4>Total: $<%= total.setScale(2, BigDecimal.ROUND_HALF_EVEN) %></h4> <!-- Mostrar total formateado -->
        </div>

        <form action="confirmarPedido" method="post" class="mt-3">
            <button type="submit" class="btn btn-success">Confirmar pedido</button>
        </form>

    <% } else { %>
        <div class="alert alert-info mt-4" role="alert">
            Tu carrito está vacío.
        </div>
    <% } %>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
