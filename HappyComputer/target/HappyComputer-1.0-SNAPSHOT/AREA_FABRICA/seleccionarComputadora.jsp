<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Seleccionar Computadora</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="flex flex-col items-center justify-center min-h-screen bg-gray-100 relative">

<!-- Botón para volver al Dashboard -->
<a href="/HappyComputer_war/AREA_FABRICA/dashboardFabrica.jsp"
   class="absolute top-4 left-4 flex items-center bg-gray-500 text-white px-4 py-2 rounded-md hover:bg-gray-600 transition">
    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor"
         class="w-5 h-5 mr-2">
        <path stroke-linecap="round" stroke-linejoin="round" d="M15.75 19.5L8.25 12l7.5-7.5"/>
    </svg>
    Volver al Dashboard
</a>

<div class="w-full max-w-md p-6 bg-white rounded-lg shadow-md">
    <h1 class="text-2xl font-bold text-center text-gray-800 mb-4">Computadoras Disponibles</h1>

    <form action="${pageContext.request.contextPath}/SvSeleccionarComputadora" method="post" class="space-y-4">
        <div>
            <label for="idComputadora" class="block text-sm font-medium text-gray-700">Selecciona una
                computadora:</label>
            <select name="idComputadora" id="idComputadora" required
                    class="w-full mt-1 p-2 border rounded-md focus:ring focus:ring-blue-300">
                <c:forEach var="computadora" items="${computadoras}">
                    <option value="${computadora.id}">${computadora.nombre}</option>
                </c:forEach>
            </select>
        </div>

        <button type="submit"
                class="w-full px-4 py-2 text-white bg-blue-600 rounded-md hover:bg-blue-700 font-bold">
            Seleccionar
        </button>
    </form>
</div>

</body>
</html>
