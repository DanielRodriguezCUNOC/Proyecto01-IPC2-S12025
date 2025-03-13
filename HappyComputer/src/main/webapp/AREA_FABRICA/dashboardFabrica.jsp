<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashboard Fábrica</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.0.0/dist/tailwind.min.css" rel="stylesheet">
    <!-- Icono -->
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/assemble.png">
</head>
<body class="bg-gray-100">
<div class="container mx-auto p-4">
    <h1 class="text-2xl font-bold mb-6 text-center">Área de Ensamblaje</h1>
    <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">

        <div class="bg-blue-500 text-white p-6 rounded-lg shadow-lg text-center">
            <h2 class="text-lg font-semibold">Ensamblar Computadora</h2>
            <p class="text-sm mb-2">Crea una nueva computadora ensamblada.</p>
            <a href="${pageContext.request.contextPath}/SvSeleccionarComputadora" class="underline">Ir a Ensamblaje</a>
        </div>

        <div class="bg-green-500 text-white p-6 rounded-lg shadow-lg text-center">
            <h2 class="text-lg font-semibold">Información de Componentes</h2>
            <p class="text-sm mb-2">Consulta y modifica la información de los componentes.</p>
            <a href="${pageContext.request.contextPath}/SvPieza" class="underline">Ver Componentes</a>
        </div>

        <div class="bg-yellow-500 text-white p-6 rounded-lg shadow-lg text-center">
            <h2 class="text-lg font-semibold">Listar Computadoras</h2>
            <p class="text-sm mb-2">Consulta las computadoras ensambladas.</p>
            <a href="${pageContext.request.contextPath}/SvInventarioComputadora?action" class="underline">Ver
                Listado</a>
        </div>

        <div class="bg-red-500 text-white p-6 rounded-lg shadow-lg text-center">
            <h2 class="text-lg font-semibold">Componentes Agotados</h2>
            <p class="text-sm mb-2">Consulta los componentes agotados o con stock bajo.</p>
            <a href="${pageContext.request.contextPath}/SvInventarioPieza?action=ordenar&orden=ASC" class="underline">Ver
                Componentes</a>
        </div>

        <div class="bg-purple-500 text-white p-6 rounded-lg shadow-lg text-center">
            <h2 class="text-lg font-semibold">Agregar Componente</h2>
            <p class="text-sm mb-2">Agrega un componente a la lista.</p>
            <a href="/HappyComputer_war/AREA_FABRICA/crearPieza.jsp" class="underline">Añadir Componente</a>
        </div>

        <div class="bg-pink-500 text-white p-6 rounded-lg shadow-lg text-center">
            <h2 class="text-lg font-semibold">Agregar Piezas al Inventario</h2>
            <p class="text-sm mb-2">Agrega más cantidad de una pieza ya existente al inventario.</p>
            <a href="${pageContext.request.contextPath}/SvInventarioPieza" class="underline">Añadir Piezas</a>
        </div>
    </div>
</div>
</body>
</html>
