<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Componentes Agotados</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100">
<div class="container mx-auto p-4">
  <h1 class="text-2xl font-bold mb-4">Componentes Agotados o a Punto de Agotarse</h1>
  <div class="mb-4">
    <a href="/SvInventarioPieza?action=ordenar&orden=ASC" class="bg-blue-500 text-white px-4 py-2 rounded">Ordenar Ascendente</a>
    <a href="/SvInventarioPieza?action=ordenar&orden=DESC" class="bg-blue-500 text-white px-4 py-2 rounded ml-2">Ordenar Descendente</a>
  </div>
  <table class="min-w-full bg-white border">
    <thead>
    <tr>
      <th class="py-2 px-4 border">ID</th>
      <th class="py-2 px-4 border">ID Pieza</th>
      <th class="py-2 px-4 border">Cantidad</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="pieza" items="${piezas}">
      <tr class="${pieza.cantidad <= 5 ? 'bg-red-100' : ''}">
        <td class="py-2 px-4 border">${pieza.id}</td>
        <td class="py-2 px-4 border">${pieza.idPieza}</td>
        <td class="py-2 px-4 border">${pieza.cantidad}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>
</body>
</html>