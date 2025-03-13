<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Ensamblar Computadora</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.0.0/dist/tailwind.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

</head>
<body class="bg-gray-100">
<div class="container mx-auto p-4">
    <!-- Botón para volver al Dashboard -->
    <a href="${pageContext.request.contextPath}/AREA_ADMIN/dashboardAdministracion.jsp"
       class="bg-gray-500 text-white px-4 py-2 rounded mb-4 inline-block">Volver al Dashboard de Administracion</a>

    <h1 class="text-2xl font-bold mb-4">Crear Computadora</h1>
    <form action="${pageContext.request.contextPath}/SvCrearComputadora" method="post"
          class="bg-white p-6 rounded shadow-md" onsubmit="return validateForm()">
        <div class="mb-4">
            <label for="nombreComputadora" class="block text-gray-700">Nombre de la Computadora</label>
            <input type="text" id="nombreComputadora" name="nombreComputadora" class="w-full px-4 py-2 border rounded"
                   required>
        </div>
        <div class="mb-4">
            <label for="precioVenta" class="block text-gray-700">Precio de Venta</label>
            <input type="number" id="precioVenta" name="precioVenta" step="0.01" class="w-full px-4 py-2 border rounded"
                   required>
        </div>
        <div class="mb-4">
            <h2 class="text-xl font-semibold mb-2">Seleccionar Piezas</h2>
            <c:forEach var="pieza" items="${piezas}">
                <div class="flex items-center mb-2">
                    <input type="checkbox" name="piezas" value="${pieza.id}" class="mr-2">
                    <span class="text-gray-700">${pieza.nombre} (Costo: $${pieza.costo})</span>
                    <input type="number" name="cantidades" min="1" value="1" class="ml-4 px-2 py-1 border rounded">
                </div>
            </c:forEach>
        </div>
        <button type="submit" class="bg-blue-500 text-white px-4 py-2 rounded">Ensamblar Computadora</button>
    </form>
</div>
<script>
    function validateForm() {
        const piezas = document.querySelectorAll('input[name="piezas"]:checked');
        if (piezas.length === 0) {
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Selecciona al menos una pieza',
                confirmButtonColor: "#3085d6"
            });
            return false;
        }
        return true;
    }
</script>
</body>
</html>