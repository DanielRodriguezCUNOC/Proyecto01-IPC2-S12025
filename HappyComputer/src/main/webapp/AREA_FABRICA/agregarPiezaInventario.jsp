<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Agregar Pieza al Inventario</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.0.0/dist/tailwind.min.css" rel="stylesheet">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/assemble.png">
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
<div class="container mx-auto p-4">
    <h1 class="text-2xl font-bold mb-4">Agregar Pieza al Inventario</h1>
    <form action="${pageContext.request.contextPath}/SvInventarioPieza" method="post"
          class="bg-white p-6 rounded shadow-md">
        <!-- Campo oculto para indicar la acción "agregarInventario" -->
        <input type="hidden" name="action" value="agregarInventario">

        <!-- Seleccionar la pieza -->
        <div class="mb-4">
            <label for="idPieza" class="block text-gray-700">Pieza</label>
            <select id="idPieza" name="idPieza" class="w-full px-4 py-2 border rounded" required>
                <c:out value="Número de piezas en el inventario: ${inventarioPiezas.size()}"/>
                <c:out value="Número de piezas en la tabla Pieza: ${piezas.size()}"/>
                <c:forEach var="inventarioPieza" items="${inventarioPiezas}">
                    <!-- Buscar el nombre de la pieza correspondiente al idPieza -->
                    <c:set var="nombrePieza" value="No encontrado"/>
                    <c:forEach var="pieza" items="${piezas}">
                        <c:if test="${pieza.id == inventarioPieza.idPieza}">
                            <c:set var="nombrePieza" value="${pieza.nombre}"/>
                        </c:if>
                    </c:forEach>
                    <!-- Mostrar la opción con el nombre de la pieza -->
                    <option value="${inventarioPieza.idPieza}">${nombrePieza} (Cantidad: ${inventarioPieza.cantidad})
                    </option>
                </c:forEach>
            </select>
        </div>

        <!-- Campo para la cantidad de piezas -->
        <div class="mb-4">
            <label for="cantidad" class="block text-gray-700">Cantidad</label>
            <input type="number" id="cantidad" name="cantidad" min="1" class="w-full px-4 py-2 border rounded" required>
        </div>

        <!-- Botón para enviar el formulario -->
        <button type="submit" class="bg-blue-500 text-white px-4 py-2 rounded">Agregar al Inventario</button>
    </form>
</div>
</body>
</html>