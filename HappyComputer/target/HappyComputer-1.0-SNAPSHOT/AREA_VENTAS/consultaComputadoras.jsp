<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Computadoras Disponibles</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100">
<div class="container mx-auto p-4">
    <h1 class="text-2xl font-bold mb-4">Computadoras Disponibles</h1>
    <table class="w-full bg-white rounded-lg shadow-lg">
        <thead>
        <tr>
            <th class="p-2">Nombre</th>
            <th class="p-2">Precio</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="computadora" items="${computadoras}">
            <tr>
                <td class="p-2">${computadora.nombre}</td>
                <td class="p-2">${computadora.precioVenta}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>