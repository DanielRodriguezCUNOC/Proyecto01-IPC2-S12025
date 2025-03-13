<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Generar Reporte de Usuario con Más Ganancias</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.0.0/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100">
<div class="container mx-auto p-4">
    <h1 class="text-2xl font-bold mb-6 text-center">Generar Reporte de Usuario con Más Ganancias</h1>
    <form action="${pageContext.request.contextPath}/SvReporteUsuario" method="post"
          class="max-w-md mx-auto bg-white p-6 rounded-lg shadow-md">
        <div class="mb-4">
            <label for="fechaInicio" class="block text-sm font-medium text-gray-700">Fecha de Inicio</label>
            <input type="date" id="fechaInicio" name="fechaInicio"
                   class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500"
                   required>
        </div>
        <div class="mb-4">
            <label for="fechaFin" class="block text-sm font-medium text-gray-700">Fecha de Fin</label>
            <input type="date" id="fechaFin" name="fechaFin"
                   class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500"
                   required>
        </div>
        <div class="text-center">
            <button type="submit" class="bg-blue-500 text-white px-4 py-2 rounded-md hover:bg-blue-600">Generar
                Reporte
            </button>
        </div>
    </form>
</div>
</body>
</html>