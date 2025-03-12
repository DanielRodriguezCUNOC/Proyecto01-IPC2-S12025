<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Consulta de Compras por Cliente</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100">
<div class="container mx-auto p-4">
    <h1 class="text-2xl font-bold mb-4">Compras del Cliente</h1>

    <!-- Mostrar un mensaje si no hay ventas -->
    <c:if test="${empty ventas}">
        <p class="text-red-500">No se encontraron compras para el cliente en el rango de fechas especificado.</p>
    </c:if>

    <!-- Tabla para mostrar las ventas -->
    <c:if test="${not empty compras}">
        <table class="w-full bg-white rounded-lg shadow-lg">
            <thead>
            <tr>
                <th class="p-2">ID Venta</th>
                <th class="p-2">NIT Cliente</th>
                <th class="p-2">Vendedor</th>
                <th class="p-2">Fecha</th>
                <th class="p-2">Total</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="compra" items="${compras}">
                <tr>
                    <td class="p-2">${compra.id}</td>
                    <td class="p-2">${compra.idCliente}</td>
                    <td class="p-2">${compra.usuario}</td>
                    <td class="p-2">${compra.fechaVenta}</td>
                    <td class="p-2">${compra.totalVenta}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>
</body>
</html>