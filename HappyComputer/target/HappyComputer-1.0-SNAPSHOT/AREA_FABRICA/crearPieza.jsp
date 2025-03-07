<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Crear Pieza</title>
  <script src="https://cdn.tailwindcss.com"></script>
  <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body class="bg-gray-100">
<div class="container mx-auto p-4">
  <!-- Botón para volver al listado de piezas -->
  <a href="${pageContext.request.contextPath}/SvPieza" class="bg-gray-500 text-white px-4 py-2 rounded mb-4 inline-block">Volver a Piezas</a>

  <h1 class="text-2xl font-bold mb-4">Crear Nueva Pieza</h1>
  <form action="${pageContext.request.contextPath}/SvPieza" method="post" class="bg-white p-6 rounded shadow-md">
    <!-- Campo oculto para indicar la acción "crear" -->
    <input type="hidden" name="action" value="crear">

    <!-- Campo para el nombre de la pieza -->
    <div class="mb-4">
      <label for="nombre" class="block text-gray-700">Nombre</label>
      <input type="text" id="nombre" name="nombre" class="w-full px-4 py-2 border rounded" required>
    </div>

    <!-- Campo para el costo de la pieza -->
    <div class="mb-4">
      <label for="costo" class="block text-gray-700">Costo</label>
      <input type="number" id="costo" name="costo" step="0.01" class="w-full px-4 py-2 border rounded" required>
    </div>

    <!-- Campo para la cantidad de la pieza -->
    <div class="mb-4">
      <label for="cantidad" class="block text-gray-700">Cantidad</label>
      <input type="number" id="cantidad" name="cantidad" min="1" class="w-full px-4 py-2 border rounded" required>
    </div>

    <!-- Botón para enviar el formulario -->
    <button type="submit" class="bg-blue-500 text-white px-4 py-2 rounded">Crear Pieza</button>
  </form>
</div>
<script>
  // Validar si la pieza ya existe antes de enviar el formulario
  document.getElementById('crearPiezaForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Evitar el envío del formulario

    const nombre = document.getElementById('nombre').value;

    // Verificar si la pieza ya existe
    fetch(`${pageContext.request.contextPath}/SvPieza?action=verificar&nombre=${encodeURIComponent(nombre)}`)
            .then(response => response.json())
            .then(data => {
              if (data.existe) {
                // Mostrar mensaje de error con SweetAlert
                Swal.fire({
                  icon: 'error',
                  title: 'Error',
                  text: 'La pieza ya existe. Por favor, ingrese un nombre diferente.',
                });
              } else {
                // Enviar el formulario si la pieza no existe
                event.target.submit();
              }
            })
            .catch(error => {
              console.error('Error al verificar la pieza:', error);
            });
  });
</script>
</body>
</html>