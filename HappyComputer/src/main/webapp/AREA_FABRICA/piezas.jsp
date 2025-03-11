<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Piezas</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/assemble.png">
</head>
<body class="bg-gray-100">
<div class="flex flex-col items-center justify-center min-h-screen bg-gray-100 relative">

    <!-- Contenedor de botones -->
    <div class="absolute top-4 left-4 flex gap-4">
        <!-- Botón para volver al Dashboard -->
        <a href="/HappyComputer_war/AREA_FABRICA/dashboardFabrica.jsp"
           class="flex items-center bg-gray-500 text-white px-4 py-2 rounded-md hover:bg-gray-600 transition">
            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="2"
                 stroke="currentColor" class="w-5 h-5 mr-2">
                <path stroke-linecap="round" stroke-linejoin="round" d="M15.75 19.5L8.25 12l7.5-7.5"/>
            </svg>
            Volver al Dashboard
        </a>

        <!-- Botón para regresar a agregar pieza -->
        <a href="/HappyComputer_war/AREA_FABRICA/crearPieza.jsp"
           class="flex items-center bg-blue-500 text-white px-4 py-2 rounded-md hover:bg-blue-600 transition">
            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="2"
                 stroke="currentColor" class="w-5 h-5 mr-2">
                <path stroke-linecap="round" stroke-linejoin="round" d="M15.75 19.5L8.25 12l7.5-7.5"/>
            </svg>
            Regresar a Agregar Pieza
        </a>
    </div>

    <div class="relative flex flex-col items-center w-full max-w-4xl p-6 bg-white rounded-lg shadow-md mt-16">
        <h1 class="text-2xl font-bold mb-4">Lista de Piezas</h1>
        <table class="min-w-full bg-white border">
            <thead>
            <tr>
                <th class="py-2 px-4 border">ID</th>
                <th class="py-2 px-4 border">Nombre</th>
                <th class="py-2 px-4 border">Costo</th>
                <th class="py-2 px-4 border">Acciones</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="pieza" items="${piezas}">
                <tr>
                    <td class="py-2 px-4 border">${pieza.id}</td>
                    <td class="py-2 px-4 border">${pieza.nombre}</td>
                    <td class="py-2 px-4 border">${pieza.costo}</td>
                    <td class="py-2 px-4 border">
                        <a href="${pageContext.request.contextPath}/SvPieza?action=editar&id=${pieza.id}"
                           class="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600">Editar</a>
                        <form action="${pageContext.request.contextPath}/SvPieza" method="post" class="inline">
                            <input type="hidden" name="action" value="eliminar">
                            <input type="hidden" name="id" value="${pieza.id}">
                            <button type="submit" class="bg-red-500 text-white px-2 py-1 rounded hover:bg-red-600">
                                Eliminar
                            </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
