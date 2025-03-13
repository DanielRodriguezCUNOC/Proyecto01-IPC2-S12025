<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Crear Pieza</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.0.0/dist/tailwind.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body class="flex flex-col items-center justify-center min-h-screen bg-gray-100 relative">
<div class="container mx-auto p-4">
    <!-- Botón para volver al Dashboard -->
    <a href="/HappyComputer_war/AREA_FABRICA/dashboardFabrica.jsp"
       class="absolute top-4 left-4 flex items-center bg-gray-500 text-white px-4 py-2 rounded-md hover:bg-gray-600 transition">
        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor"
             class="w-5 h-5 mr-2">
            <path stroke-linecap="round" stroke-linejoin="round" d="M15.75 19.5L8.25 12l7.5-7.5"/>
        </svg>
        Volver al Dashboard
    </a>
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
    document.getElementById('crearPiezaForm').addEventListener('submit', function (event) {
        event.preventDefault(); // Evitar el envío del formulario

        const nombre = document.getElementById('nombre').value;

        //Codificar el nombre de la pieza para enviarlo en la URL
        const nombreCodificado = encodeURIComponent(nombre);

        // Verificar si la pieza ya existe
        fetch(`${pageContext.request.contextPath}/SvPieza?action=verificar&nombre=${nombreCodificado}`)
            .then(response => response.json())
            .then(data => {
                if (data.existe) {
                    // Mostrar mensaje de error si la pieza ya existe
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