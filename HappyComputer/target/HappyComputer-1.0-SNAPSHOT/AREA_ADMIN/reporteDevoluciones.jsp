<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reporte de Devoluciones</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.0.0/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100">
<div class="container mx-auto p-4">
    <h1 class="text-2xl font-bold mb-6 text-center">Reporte de Devoluciones</h1>

    <!-- Botón para exportar a CSV -->
    <form action="${pageContext.request.contextPath}/SvExportarDevolucion" method="post" class="mb-4">
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
                <th class="px-4 py-2">ID Devolución</th>
                <th class="px-4 py-2">ID Venta</th>
                <th class="px-4 py-2">Fecha Devolución</th>
                <th class="px-4 py-2">Pérdida</th>
                <th class="px-4 py-2">Cliente</th>
                <th class="px-4 py-2">Computadora</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="devolucion" items="${devoluciones}">
                <tr class="border-b">
                    <td class="px-4 py-2 text-center">${devolucion.idDevolucion}</td>
                    <td class="px-4 py-2 text-center">${devolucion.idVenta}</td>
                    <td class="px-4 py-2 text-center">${devolucion.fechaDevolucion}</td>
                    <td class="px-4 py-2 text-center">${devolucion.perdida}</td>
                    <td class="px-4 py-2 text-center">${devolucion.nombreCliente}</td>
                    <td class="px-4 py-2 text-center">${devolucion.nombreComputadora}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>