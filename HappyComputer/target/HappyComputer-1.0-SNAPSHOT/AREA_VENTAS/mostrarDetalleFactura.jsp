<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Detalle de Factura</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100">
<div class="container mx-auto p-4">
    <h1 class="text-2xl font-bold mb-4">Detalle de Factura</h1>
    <div class="bg-white p-6 rounded-lg shadow-lg">
        <c:if test="${not empty venta}">
            <p><strong>ID Venta:</strong> ${venta.id}</p>
            <p><strong>Fecha:</strong> ${venta.fechaVenta}</p>
            <p><strong>Total:</strong> ${venta.totalVenta}</p>

            <table class="w-full mt-4 border-collapse border border-gray-300">
                <thead>
                <tr class="bg-gray-200">
                    <th class="p-2 border">Computadora</th>
                    <th class="p-2 border">Precio Unitario</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="detalle" items="${detalles}">
                    <tr>
                        <td class="p-2 border">${detalle.nombreComputadora}</td>
                        <td class="p-2 border">${detalle.precioUnidad}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>
</div>
</body>
</html>
