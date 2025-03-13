<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Editar Pieza</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.0.0/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="flex flex-col items-center justify-center min-h-screen bg-gray-100 relative">
<div class="container mx-auto p-4">
    <h1 class="text-2xl font-bold mb-4">Editar Pieza</h1>
    <!-- Botón para volver al Dashboard -->
    <a href="/HappyComputer_war/AREA_FABRICA/dashboardFabrica.jsp"
       class="absolute top-4 left-4 flex items-center bg-gray-500 text-white px-4 py-2 rounded-md hover:bg-gray-600 transition">
        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor"
             class="w-5 h-5 mr-2">
            <path stroke-linecap="round" stroke-linejoin="round" d="M15.75 19.5L8.25 12l7.5-7.5"/>
        </svg>
        Volver al Dashboard
    </a>
    <form action="${pageContext.request.contextPath}/SvPieza" method="post" class="bg-white p-6 rounded shadow-md">
        <input type="hidden" name="action" value="actualizar">
        <input type="hidden" name="id" value="${pieza.id}">
        <div class="mb-4">
            <label for="nombre" class="block text-gray-700">Nombre</label>
            <input type="text" id="nombre" name="nombre" value="${pieza.nombre}"
                   class="w-full px-4 py-2 border rounded">
        </div>
        <div class="mb-4">
            <label for="costo" class="block text-gray-700">Costo</label>
            <input type="number" id="costo" name="costo" step="0.01" value="${pieza.costo}"
                   class="w-full px-4 py-2 border rounded">
        </div>
        <button type="submit" class="bg-blue-500 text-white px-4 py-2 rounded">Guardar Cambios</button>
    </form>
</div>
</body>
</html>