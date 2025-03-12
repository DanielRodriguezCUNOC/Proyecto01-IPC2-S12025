<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reporte de Ventas</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.0.0/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100">
<div class="container mx-auto p-4">
    <h1 class="text-2xl font-bold mb-6 text-center">Reporte de Ventas</h1>

    <!-- Botón para exportar a CSV -->
    <form action="${pageContext.request.contextPath}/ExportarCSVServlet" method="post" class="mb-4">
        <input type="hidden" name="fechaInicio" value="${param.fechaInicio}">
        <input type="hidden" name="fechaFin" value="${param.fechaFin}">
        <button type="submit" class="bg-green-500 text-white px-4 py-2 rounded-md hover:bg-green-600">Exportar a CSV
        </button>
    </form>

    <!-- Tabla de resultados -->
    <div class="bg-white rounded-lg shadow-md overflow-hidden">
        <table class="min-w-full">
            <thead class="bg-gray-200">
            <tr>
                <th class="px-4 py-2">ID Venta</th>
                <th class="px-4 py-2">Cliente</th>
                <th class="px-4 py-2">Fecha Venta</th>
                <th class="px-4 py-2">Computadora</th>
                <th class="px-4 py-2">Precio Unitario</th>
                <th class="px-4 py-2">Cantidad</th>
                <th class="px-4 py-2">Total Venta</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="venta" items="${ventas}">
                <tr class="border-b">
                    <td class="px-4 py-2 text-center">${venta.idVenta}</td>
                    <td class="px-4 py-2 text-center">${venta.nombreCliente}</td>
                    <td class="px-4 py-2 text-center">${venta.fechaVenta}</td>
                    <td class="px-4 py-2 text-center">${venta.nombreComputadora}</td>
                    <td class="px-4 py-2 text-center">${venta.precioUnitario}</td>
                    <td class="px-4 py-2 text-center">${venta.cantidad}</td>
                    <td class="px-4 py-2 text-center">${venta.totalVenta}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>