<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registrar Venta</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100">
<div class="container mx-auto p-4">
    <h1 class="text-2xl font-bold mb-4">Registrar Venta</h1>
    <form action="/VentaServlet" method="post" class="bg-white p-6 rounded-lg shadow-lg">
        <div class="mb-4">
            <label for="nit" class="block text-gray-700">NIT del Cliente:</label>
            <input type="text" id="nit" name="nit" class="w-full p-2 border border-gray-300 rounded-lg" required>
        </div>
        <div class="mb-4">
            <label for="computadoras" class="block text-gray-700">Computadoras Disponibles:</label>
            <select id="computadoras" name="computadoras" multiple class="w-full p-2 border border-gray-300 rounded-lg"
                    required>
                <!-- Aquí se cargarán las computadoras disponibles desde la base de datos -->
            </select>
        </div>
        <button type="submit" class="bg-blue-500 text-white p-2 rounded-lg">Registrar Venta</button>
    </form>
</div>
</body>
</html>
