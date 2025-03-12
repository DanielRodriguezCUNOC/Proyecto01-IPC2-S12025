<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registrar Cliente</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100">
<div class="container mx-auto p-4">
    <h1 class="text-2xl font-bold mb-4">Registrar Cliente</h1>
    <form action="/SvRegistrarCliente" method="post" class="bg-white p-6 rounded-lg shadow-lg">
        <div class="mb-4">
            <label for="nit" class="block text-gray-700">NIT:</label>
            <input type="text" id="nit" name="nit" class="w-full p-2 border border-gray-300 rounded-lg" required>
        </div>
        <div class="mb-4">
            <label for="nombre" class="block text-gray-700">Nombre:</label>
            <input type="text" id="nombre" name="nombre" class="w-full p-2 border border-gray-300 rounded-lg" required>
        </div>
        <div class="mb-4">
            <label for="direccion" class="block text-gray-700">Dirección:</label>
            <input type="text" id="direccion" name="direccion" class="w-full p-2 border border-gray-300 rounded-lg"
                   required>
        </div>
        <button type="submit" class="bg-blue-500 text-white p-2 rounded-lg">Registrar</button>
    </form>
</div>
</body>
</html>