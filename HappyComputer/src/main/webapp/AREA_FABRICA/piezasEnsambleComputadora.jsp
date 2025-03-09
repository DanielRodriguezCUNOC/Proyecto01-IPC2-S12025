<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Piezas Necesarias</title>
  <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="flex items-center justify-center min-h-screen bg-gray-100">
<div class="w-full max-w-3xl p-6 bg-white rounded-lg shadow-md">
  <h1 class="text-2xl font-bold text-center text-gray-800 mb-4">
    Piezas Necesarias para ${computadora.nombre}
  </h1>

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
            <td class="px-4 py-2 border text-center">${pieza.pieza.nombre}</td>
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
