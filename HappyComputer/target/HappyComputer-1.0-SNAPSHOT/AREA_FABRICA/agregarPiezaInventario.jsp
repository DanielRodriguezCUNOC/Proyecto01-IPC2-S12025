<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Agregar Pieza al Inventario</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/assemble.png">
</head>
<body class="bg-gray-100">
<div class="container mx-auto p-4">
    <!-- Boton para regresar al dashboard -->
    <a href="/HappyComputer_war/AREA_FABRICA/dashboardFabrica.jsp" class="bg-gray-500 text-white px-4 py-2 rounded mb-4 inline-block">Regresar al Dashboard Ensamblaje</a>
    <!-- Botón para volver al listado de piezas -->
    <a href="${pageContext.request.contextPath}/SvPieza" class="bg-gray-500 text-white px-4 py-2 rounded mb-4 inline-block">Volver a Piezas</a>

    <h1 class="text-2xl font-bold mb-4">Agregar Pieza al Inventario</h1>
    <form action="${pageContext.request.contextPath}/SvPieza" method="post" class="bg-white p-6 rounded shadow-md">
        <!-- Campo oculto para indicar la acción "agregarInventario" -->
        <input type="hidden" name="action" value="agregarInventario">

        <!-- Seleccionar la pieza -->
        <div class="mb-4">
            <label for="idPieza" class="block text-gray-700">Pieza</label>
            <select id="idPieza" name="idPieza" class="w-full px-4 py-2 border rounded" required>
                <c:forEach var="pieza" items="${piezas}">
                    <option value="${pieza.id}">${pieza.nombre}</option>
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