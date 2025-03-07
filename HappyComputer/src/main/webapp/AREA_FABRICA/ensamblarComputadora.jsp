<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Ensamblar Computadora</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100">
<div class="container mx-auto p-4">
    <h1 class="text-2xl font-bold mb-4">Ensamblar Computadora</h1>
    <form action="${pageContext.request.contextPath}/SvEnsamblaje" method="post" class="bg-white p-6 rounded shadow-md">
        <div class="mb-4">
            <label for="nombreComputadora" class="block text-gray-700">Nombre de la Computadora</label>
            <input type="text" id="nombreComputadora" name="nombreComputadora" class="w-full px-4 py-2 border rounded">
        </div>
        <div class="mb-4">
            <label for="precioVenta" class="block text-gray-700">Precio de Venta</label>
            <input type="number" id="precioVenta" name="precioVenta" step="0.01" class="w-full px-4 py-2 border rounded">
        </div>
        <div class="mb-4">
            <h2 class="text-xl font-semibold mb-2">Seleccionar Piezas</h2>
            <c:forEach var="pieza" items="${piezas}">
                <div class="flex items-center mb-2">
                    <input type="checkbox" name="piezas" value="${pieza.id}" class="mr-2">
                    <span class="text-gray-700">${pieza.nombre} (Costo: $${pieza.costo})</span>
                    <input type="number" name="cantidades" min="1" value="1" class="ml-4 px-2 py-1 border rounded">
                </div>
            </c:forEach>
        </div>
        <button type="submit" class="bg-blue-500 text-white px-4 py-2 rounded">Ensamblar Computadora</button>
    </form>
</div>
</body>
</html>