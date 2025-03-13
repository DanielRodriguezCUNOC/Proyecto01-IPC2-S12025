<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Computadoras Ensambladas</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.0.0/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100">
<div class="min-h-screen flex flex-col">
    <div class="container mx-auto p-4 relative">
        <!-- Botón para volver al Dashboard -->
        <a href="/HappyComputer_war/AREA_FABRICA/dashboardFabrica.jsp"
           class="absolute top-4 left-4 flex items-center bg-gray-500 text-white px-4 py-2 rounded-md hover:bg-gray-600 transition">
            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="2"
                 stroke="currentColor"
                 class="w-5 h-5 mr-2">
                <path stroke-linecap="round" stroke-linejoin="round" d="M15.75 19.5L8.25 12l7.5-7.5"/>
            </svg>
            Volver al Dashboard
        </a>

        <h1 class="text-2xl font-bold text-center mt-16 mb-4">Computadoras Ensambladas</h1>

        <div class="mb-4 flex justify-center">
            <a href="${pageContext.request.contextPath}/SvInventarioComputadora?action=ordenar&orden=ASC"
               class="bg-blue-500 text-white px-4 py-2 rounded">Ordenar Ascendente</a>
            <a href="${pageContext.request.contextPath}/SvInventarioComputadora?action=ordenar&orden=DESC"
               class="bg-blue-500 text-white px-4 py-2 rounded ml-2">Ordenar Descendente</a>
        </div>

        <table class="min-w-full bg-white border">
            <thead>
            <tr>
                <th class="py-2 px-4 border">ID</th>
                <th class="py-2 px-4 border">ID Computadora</th>
                <th class="py-2 px-4 border">ID Usuario</th>
                <th class="py-2 px-4 border">Fecha de Ensamblado</th>
                <th class="py-2 px-4 border">Costo</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="computadora" items="${computadoras}">
                <tr>
                    <td class="py-2 px-4 border">${computadora.id}</td>
                    <td class="py-2 px-4 border">${computadora.idComputadora}</td>
                    <td class="py-2 px-4 border">${computadora.idUsuario}</td>
                    <td class="py-2 px-4 border">${computadora.fechaEnsamble}</td>
                    <td class="py-2 px-4 border">${computadora.costoEnsamble}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
