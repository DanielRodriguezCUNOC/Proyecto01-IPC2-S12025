<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Consultar Devoluciones</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100">
<div class="container mx-auto p-4">
    <h1 class="text-2xl font-bold mb-4">Consultar Devoluciones</h1>
    <form action="/SvDevolucion" method="get" class="bg-white p-6 rounded-lg shadow-lg">
        <div class="mb-4">
            <label for="nit" class="block text-gray-700">NIT del Cliente:</label>
            <input type="text" id="nit" name="nit" class="w-full p-2 border border-gray-300 rounded-lg" required>
        </div>
        <div class="mb-4">
            <label for="fechaInicio" class="block text-gray-700">Fecha Inicio:</label>
            <input type="date" id="fechaInicio" name="fechaInicio" class="w-full p-2 border border-gray-300 rounded-lg"
                   required>
        </div>
        <div class="mb-4">
            <label for="fechaFin" class="block text-gray-700">Fecha Fin:</label>
            <input type="date" id="fechaFin" name="fechaFin" class="w-full p-2 border border-gray-300 rounded-lg"
                   required>
        </div>
        <button type="submit" class="bg-blue-500 text-white p-2 rounded-lg">Consultar</button>
    </form>
</div>
</body>
</html>