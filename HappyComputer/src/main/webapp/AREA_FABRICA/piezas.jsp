
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Piezas</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/assemble.png">
</head>
<body class="bg-gray-100">
<div class="container mx-auto p-4">
    <!-- Boton para regresar al dashboard -->
    <a href="/HappyComputer_war/AREA_FABRICA/dashboardFabrica.jsp" class="bg-gray-500 text-white px-4 py-2 rounded mb-4 inline-block">Regresar al Dashboard Ensamblaje</a>
    <!-- Boton para regresar a agregar pieza -->
    <a href="/HappyComputer_war/AREA_FABRICA/crearPieza.jsp" class="bg-blue-500 text-white px-4 py-2 rounded mb-4 inline-block">Regresar a Agregar Pieza</a>
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
                    <a href="${pageContext.request.contextPath}/SvPieza?action=editar&id=${pieza.id}" class="bg-blue-500 text-white px-4 py-2 rounded">Editar</a>
                    <form action="${pageContext.request.contextPath}/SvPieza" method="post" class="inline">
                        <input type="hidden" name="action" value="eliminar">
                        <input type="hidden" name="id" value="${pieza.id}">
                        <button type="submit" class="bg-red-500 text-white px-2 py-1 rounded">Eliminar</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
