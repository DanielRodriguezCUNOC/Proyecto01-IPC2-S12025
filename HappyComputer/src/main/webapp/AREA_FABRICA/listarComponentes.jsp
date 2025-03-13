<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Componentes Agotados</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.0.0/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100">
<div class="flex flex-col items-center justify-center min-h-screen bg-gray-100 relative">
    <!-- Botón para volver al Dashboard -->
    <a href="/HappyComputer_war/AREA_FABRICA/dashboardFabrica.jsp"
       class="absolute top-4 left-4 flex items-center bg-gray-500 text-white px-4 py-2 rounded-md hover:bg-gray-600 transition">
        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor"
             class="w-5 h-5 mr-2">
            <path stroke-linecap="round" stroke-linejoin="round" d="M15.75 19.5L8.25 12l7.5-7.5"/>
        </svg>
        Volver al Dashboard
    </a>
    <div class="container mx-auto p-4">
        <h1 class="text-2xl font-bold mb-4">Componentes Agotados o a Punto de Agotarse</h1>
        <div class="mb-4">
            <a href="/SvInventarioPieza?action=ordenar&orden=ASC" class="bg-blue-500 text-white px-4 py-2 rounded">Ordenar
                Ascendente</a>
            <a href="/SvInventarioPieza?action=ordenar&orden=DESC"
               class="bg-blue-500 text-white px-4 py-2 rounded ml-2">Ordenar
                Descendente</a>
        </div>
        <table class="min-w-full bg-white border">
            <thead>
            <tr>
                <th class="py-2 px-4 border">Nombre de la Pieza</th>
                <th class="py-2 px-4 border">Cantidad</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="inventarioPieza" items="${piezasTotales}">
                <tr class="bg-gray-50 even:bg-gray-100">
                    <td class="px-4 py-2 border text-center">
                        <c:forEach var="pieza" items="${piezas}">
                            <c:if test="${pieza.id == inventarioPieza.idPieza}">
                                <label class="block text-gray-700">${pieza.nombre}</label>
                            </c:if>
                        </c:forEach>
                    </td>
                    <td class="px-4 py-2 border text-center">
                        <label class="block text-gray-700">${inventarioPieza.cantidad}</label>
                        <input type="hidden" name="cantidad_${inventarioPieza.idPieza}"
                               value="${inventarioPieza.cantidad}">
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>