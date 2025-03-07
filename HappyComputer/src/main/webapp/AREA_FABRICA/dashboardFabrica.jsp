<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Dashboard Fábrica</title>
  <script src="https://cdn.tailwindcss.com"></script>
  <!-- Favicon -->
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.png">
</head>
<body class="bg-gray-100">
<div class="container mx-auto p-4">
  <h1 class="text-2xl font-bold mb-4 text-center">Bienvenido al Área de Fábrica</h1>
  <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
    <!-- Ensamblar Computadora -->
    <div class="bg-white p-6 rounded shadow-md">
      <h2 class="text-xl font-semibold mb-2">Ensamblar Computadora</h2>
      <p class="text-gray-700 mb-4">Crea una nueva computadora ensamblada.</p>
      <a href="${pageContext.request.contextPath}/SvPieza?origen=ensamblar" class="bg-blue-500 text-white px-4 py-2 rounded block w-full text-center break-words">Ensamblar Computadora</a>
    </div>

    <!-- Información de Componentes -->
    <div class="bg-white p-6 rounded shadow-md">
      <h2 class="text-xl font-semibold mb-2">Información de Componentes</h2>
      <p class="text-gray-700 mb-4">Consulta y modifica la información de los componentes.</p>
      <a href="/HappyComputer_war/AREA_FABRICA/piezas.jsp" class="bg-blue-500 text-white px-4 py-2 rounded block w-full text-center break-words">Componentes</a>
    </div>

    <!-- Listar Computadoras Ensambladas -->
    <div class="bg-white p-6 rounded shadow-md">
      <h2 class="text-xl font-semibold mb-2">Listar Computadoras</h2>
      <p class="text-gray-700 mb-4">Consulta las computadoras ensambladas.</p>
      <a href="/HappyComputer_war/AREA_FABRICA/listarComputadoras.jsp" class="bg-blue-500 text-white px-4 py-2 rounded block w-full text-center break-words">Listar Computadoras</a>
    </div>

    <!-- Componentes Agotados -->
    <div class="bg-white p-6 rounded shadow-md">
      <h2 class="text-xl font-semibold mb-2">Componentes Agotados</h2>
      <p class="text-gray-700 mb-4">Consulta los componentes agotados o con stock bajo.</p>
      <a href="/HappyComputer_war/AREA_FABRICA/componentesAgotados.jsp" class="bg-blue-500 text-white px-4 py-2 rounded block w-full text-center break-words">Componentes Agotados</a>
    </div>

    <!-- Crear Componente -->
    <div class="bg-white p-6 rounded shadow-md">
      <h2 class="text-xl font-semibold mb-2">Agregar Componente</h2>
      <p class="text-gray-700 mb-4">Agrega un componente a la lista.</p>
      <a href="/HappyComputer_war/AREA_FABRICA/crearPieza.jsp" class="bg-blue-500 text-white px-4 py-2 rounded block w-full text-center break-words">Agregar Componente</a>
    </div>
  </div>
</div>
</body>
</html>
