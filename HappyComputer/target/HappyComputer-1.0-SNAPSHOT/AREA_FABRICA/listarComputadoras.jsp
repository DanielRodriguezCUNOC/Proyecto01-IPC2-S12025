<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Computadoras Ensambladas</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100">
<div class="container mx-auto p-4">
    <!-- Botón para volver al Dashboard -->
    <a href="/HappyComputer_war/AREA_FABRICA/dashboardFabrica.jsp" class="bg-gray-500 text-white px-4 py-2 rounded mb-4 inline-block">Volver al Dashboard de Ensamblaje</a>

    <h1 class="text-2xl font-bold mb-4">Computadoras Ensambladas</h1>
    <div class="mb-4">
        <a href="${pageContext.request.contextPath}/SvInventarioComputadora?action=ordenar&orden=ASC" class="bg-blue-500 text-white px-4 py-2 rounded">Ordenar Ascendente</a>
        <a href="${pageContext.request.contextPath}/SvInventarioComputadora?action=ordenar&orden=DESC" class="bg-blue-500 text-white px-4 py-2 rounded ml-2">Ordenar Descendente</a>
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
</body>
</html>