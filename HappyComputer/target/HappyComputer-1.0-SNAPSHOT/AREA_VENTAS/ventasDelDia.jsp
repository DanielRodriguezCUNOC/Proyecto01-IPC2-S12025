<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ventas del Día</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.1.2/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100">

<div class="container mx-auto mt-8">
    <h1 class="text-3xl font-bold text-center mb-4">Ventas del Día</h1>

    <!-- Mostrar mensaje si no hay ventas -->
    <c:if test="${empty ventas}">
        <p class="text-red-500 text-center">No se encontraron ventas para el día de hoy.</p>
    </c:if>

    <!-- Tabla para mostrar las ventas -->
    <c:if test="${not empty ventas}">
        <table class="min-w-full bg-white rounded-lg shadow-lg">
            <thead class="bg-gray-200">
            <tr>
                <th class="p-3 text-left">ID Venta</th>
                <th class="p-3 text-left">Vendedor</th>
                <th class="p-3 text-left">ID Cliente</th>
                <th class="p-3 text-left">Total Venta</th>
                <th class="p-3 text-left">Fecha</th>
            </tr>
            </thead>
            <tbody>
            <!-- Iterar sobre las ventas -->
            <c:forEach var="venta" items="${ventas}">
                <tr>
                    <td class="p-3 border-b">${venta.id}</td>
                    <td class="p-3 border-b">${venta.usuario}</td>
                    <td class="p-3 border-b">${venta.idCliente}</td>
                    <td class="p-3 border-b">${venta.totalVenta}</td>
                    <td class="p-3 border-b">${venta.fechaVenta}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>

</body>
</html>

