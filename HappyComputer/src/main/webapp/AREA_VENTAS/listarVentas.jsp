<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Listar Ventas</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100">
<div class="container mx-auto p-4">
    <h1 class="text-2xl font-bold mb-4">Ventas</h1>
    <div class="bg-white p-6 rounded-lg shadow-lg">
        <!-- Tabla de Ventas -->
        <table class="w-full table-auto">
            <thead>
            <tr>
                <th class="p-2 border">ID Venta</th>
                <th class="p-2 border">NIT Usuario</th>
                <th class="p-2 border">Fecha</th>
                <th class="p-2 border">Acciones</th>
            </tr>
            </thead>
            <tbody>
            <!-- Aquí recorremos las ventas y mostramos los datos -->
            <c:forEach var="venta" items="${ventas}">
                <tr>
                    <td class="p-2 border"><c:out value="${venta.id}"/></td>
                    <td class="p-2 border"><c:out value="${venta.idUsuario}"/></td>
                    <td class="p-2 border"><c:out value="${venta.fechaVenta}"/></td>
                    <td class="p-2 border">
                        <!-- Botón para ver factura -->
                        <form action="/SvFactura" method="get">
                            <input type="hidden" name="action" value="detalleFactura"/>
                            <input type="hidden" name="idVenta" value="${venta.id}">
                            <button type="submit" class="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-700">Ver
                                Factura
                            </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
