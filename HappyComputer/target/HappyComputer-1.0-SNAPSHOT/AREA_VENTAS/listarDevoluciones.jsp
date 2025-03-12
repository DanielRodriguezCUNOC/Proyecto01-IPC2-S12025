<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reporte de Devoluciones</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.0.0/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100 p-6">

<div class="max-w-4xl mx-auto bg-white p-6 rounded-lg shadow-lg">
    <!-- Mostrar un mensaje si no hay devoluciones -->
    <c:if test="${empty devoluciones}">
        <p class="text-red-500 text-center">No se encontraron devoluciones para el cliente en el rango de fechas
            especificado.</p>
    </c:if>

    <!-- Tabla para mostrar las devoluciones -->
    <c:if test="${not empty devoluciones}">
        <table class="w-full bg-white rounded-lg shadow-lg mt-4">
            <thead class="bg-gray-200">
            <tr>
                <th class="p-3 text-left">ID Devolución</th>
                <th class="p-3 text-left">ID Venta</th>
                <th class="p-3 text-left">Fecha Devolución</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="devolucion" items="${devoluciones}">
                <tr class="border-t hover:bg-gray-100">
                    <td class="p-3">${devolucion.id}</td>
                    <td class="p-3">${devolucion.idVenta}</td>
                    <td class="p-3">${devolucion.fechaDevolucion}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>

</body>
</html>

