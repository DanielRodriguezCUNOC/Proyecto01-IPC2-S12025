<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Editar Pieza</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100">
<div class="container mx-auto p-4">
    <h1 class="text-2xl font-bold mb-4">Editar Pieza</h1>
    <form action="${pageContext.request.contextPath}/SvPieza" method="post" class="bg-white p-6 rounded shadow-md">
        <input type="hidden" name="action" value="actualizar">
        <input type="hidden" name="id" value="${pieza.id}">
        <div class="mb-4">
            <label for="nombre" class="block text-gray-700">Nombre</label>
            <input type="text" id="nombre" name="nombre" value="${pieza.nombre}" class="w-full px-4 py-2 border rounded">
        </div>
        <div class="mb-4">
            <label for="costo" class="block text-gray-700">Costo</label>
            <input type="number" id="costo" name="costo" step="0.01" value="${pieza.costo}" class="w-full px-4 py-2 border rounded">
        </div>
        <button type="submit" class="bg-blue-500 text-white px-4 py-2 rounded">Guardar Cambios</button>
    </form>
</div>
</body>
</html>