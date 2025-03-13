<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Piezas Necesarias</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.0.0/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="flex items-center justify-center min-h-screen bg-gray-100">
<!-- Botón para volver al Dashboard -->
<a href="/HappyComputer_war/AREA_FABRICA/dashboardFabrica.jsp"
   class="absolute top-4 left-4 flex items-center bg-gray-500 text-white px-4 py-2 rounded-md hover:bg-gray-600 transition">
    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor"
         class="w-5 h-5 mr-2">
        <path stroke-linecap="round" stroke-linejoin="round" d="M15.75 19.5L8.25 12l7.5-7.5"/>
    </svg>
    Volver al Dashboard
</a>
<div class="w-full max-w-3xl p-6 bg-white rounded-lg shadow-md">
    <h1 class="text-2xl font-bold text-center text-gray-800 mb-4">
        Piezas Necesarias para ${computadora.nombre}
    </h1>

    <!-- Mostrar piezas agotadas o a punto de agotarse -->
    <div class="mb-6">
        <h2 class="text-xl font-semibold text-red-600 mb-2">Piezas Agotadas o a Punto de Agotarse</h2>
        <table class="w-full border-collapse border border-gray-300">
            <thead class="bg-red-600 text-white">
            <tr>
                <th class="px-4 py-2 border">Pieza</th>
                <th class="px-4 py-2 border">Cantidad</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="piezaAgotada" items="${piezasAgotadas}">
                <tr class="bg-red-50">
                    <td class="px-4 py-2 border text-center">
                        <c:forEach var="p" items="${piezas}">
                            <c:if test="${p.id == piezaAgotada.idPieza}">
                                <label class="block text-gray-700">${p.nombre}</label>
                            </c:if>
                        </c:forEach>
                    </td>
                    <td class="px-4 py-2 border text-center">
                        <label class="block text-gray-700">${piezaAgotada.cantidad}</label>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <!-- Formulario para ensamblar la computadora -->
    <form action="${pageContext.request.contextPath}/SvEnsamblaje" method="post" class="space-y-4">
        <input type="hidden" name="idComputadora" value="${computadora.id}">

        <div class="overflow-x-auto">
            <table class="w-full border-collapse border border-gray-300">
                <thead class="bg-blue-600 text-white">
                <tr>
                    <th class="px-4 py-2 border">Pieza</th>
                    <th class="px-4 py-2 border">Cantidad</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="pieza" items="${piezasNecesarias}">
                    <tr class="bg-gray-50 even:bg-gray-100">
                        <td class="px-4 py-2 border text-center">
                            <c:forEach var="p" items="${piezas}">
                                <c:if test="${p.id == pieza.idPieza}">
                                    <label class="block text-gray-700">${p.nombre}</label>
                                </c:if>
                            </c:forEach>
                        </td>
                        <td class="px-4 py-2 border text-center">
                            <label class="block text-gray-700">${pieza.cantidad}</label>
                            <input type="hidden" name="cantidad_${pieza.idPieza}" value="${pieza.cantidad}">
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <button type="submit"
                class="w-full px-4 py-2 text-white bg-blue-600 rounded-md hover:bg-blue-700 font-bold">
            Ensamblar Computadora
        </button>
    </form>
</div>
</body>
</html>