<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard Administración</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.0.0/dist/tailwind.min.css" rel="stylesheet">
    <!-- icono -->
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/admin.png">

</head>
<body class="bg-gray-100">
<div class="container mx-auto p-4">
    <h1 class="text-2xl font-bold mb-6 text-center">Área de Administración y Finanzas</h1>
    <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        <!-- Reporte de Ventas -->
        <div class="bg-blue-500 text-white p-4 rounded-lg shadow-lg text-center">
            <h2 class="text-lg font-semibold">Reporte de Ventas</h2>
            <p class="text-sm mb-2">Genera un reporte de ventas en un intervalo de tiempo.</p>
            <a href="${pageContext.request.contextPath}/AREA_ADMIN/generarReporteVentas.jsp" class="underline">Generar
                Reporte</a>
        </div>

        <!-- Reporte de Devoluciones -->
        <div class="bg-green-500 text-white p-4 rounded-lg shadow-lg text-center">
            <h2 class="text-lg font-semibold">Reporte de Devoluciones</h2>
            <p class="text-sm mb-2">Genera un reporte de devoluciones en un intervalo de tiempo.</p>
            <a href="${pageContext.request.contextPath}/AREA_ADMIN/generarReporteDevolucion.jsp" class="underline">Generar
                Reporte</a>
        </div>

        <!-- Reporte de Ganancias -->
        <div class="bg-red-500 text-white p-4 rounded-lg shadow-lg text-center">
            <h2 class="text-lg font-semibold">Reporte de Ganancias</h2>
            <p class="text-sm mb-2">Genera un reporte de ganancias en un intervalo de tiempo.</p>
            <a href="${pageContext.request.contextPath}/AREA_ADMIN/generarReporteGanancias.jsp" class="underline">Generar
                Reporte</a>
        </div>

        <!-- Reporte de Usuario con Más Ventas -->
        <div class="bg-yellow-500 text-white p-4 rounded-lg shadow-lg text-center">
            <h2 class="text-lg font-semibold">Usuario con Más Ventas</h2>
            <p class="text-sm mb-2">Genera un reporte del usuario con más ventas en un intervalo de tiempo.</p>
            <a href="${pageContext.request.contextPath}/AREA_ADMIN/generarReporteUsuarioMasVentas.jsp"
               class="underline">Generar
                Reporte</a>
        </div>

        <!-- Reporte de Usuario con Más Ganancias -->
        <div class="bg-purple-500 text-white p-4 rounded-lg shadow-lg text-center">
            <h2 class="text-lg font-semibold">Usuario con Más Ganancias</h2>
            <p class="text-sm mb-2">Genera un reporte del usuario con más ganancias en un intervalo de tiempo.</p>
            <a href="${pageContext.request.contextPath}/AREA_ADMIN/generarReporteUsuarioMasGanancias.jsp"
               class="underline">Generar
                Reporte</a>
        </div>

        <!-- Reporte de Computadora Más Vendida -->
        <div class="bg-pink-500 text-white p-4 rounded-lg shadow-lg text-center">
            <h2 class="text-lg font-semibold">Computadora Más Vendida</h2>
            <p class="text-sm mb-2">Genera un reporte de la computadora más vendida en un intervalo de tiempo.</p>
            <a href="${pageContext.request.contextPath}/AREA_ADMIN/reporteComputadoraMasVendida.jsp" class="underline">Generar
                Reporte</a>
        </div>

        <!-- Reporte de Computadora Menos Vendida -->
        <div class="bg-indigo-500 text-white p-4 rounded-lg shadow-lg text-center">
            <h2 class="text-lg font-semibold">Computadora Menos Vendida</h2>
            <p class="text-sm mb-2">Genera un reporte de la computadora menos vendida en un intervalo de tiempo.</p>
            <a href="${pageContext.request.contextPath}/AREA_ADMIN/reporteComputadoraMenosVendida.jsp"
               class="underline">Generar
                Reporte</a>
        </div>

        <!-- Crear Nuevo Tipo de Computadora -->
        <div class="bg-yellow-600 text-white p-4 rounded-lg shadow-lg text-center">
            <h2 class="text-lg font-semibold">Crear Tipo de Computadora</h2>
            <p class="text-sm mb-2">Define nuevos tipos de computadoras y su precio de venta.</p>
            <a href="${pageContext.request.contextPath}/SvCrearComputadora" class="underline">Crear
                Tipo</a>
        </div>

        <!-- Crear y Cancelar Usuarios -->
        <div class="bg-blue-600 text-white p-4 rounded-lg shadow-lg text-center">
            <h2 class="text-lg font-semibold">Gestión de Usuarios</h2>
            <p class="text-sm mb-2">Crea o cancela usuarios del sistema.</p>
            <a href="${pageContext.request.contextPath}/AREA_ADMIN/gestionUsuarios.jsp" class="underline">Gestionar
                Usuarios</a>
        </div>

        <!-- Dashboard de Ventas -->
        <div class="bg-gray-800 text-white p-4 rounded-lg shadow-lg text-center">
            <h2 class="text-lg font-semibold">Dashboard de Ventas</h2>
            <p class="text-sm mb-2">Accede al área de ventas para gestionar transacciones.</p>
            <a href="${pageContext.request.contextPath}/AREA_VENTAS/dashboardVentas.jsp" class="underline">Ir a
                Ventas</a>
        </div>

        <!-- Dashboard de Ensamblaje -->
        <div class="bg-red-800 text-white p-4 rounded-lg shadow-lg text-center">
            <h2 class="text-lg font-semibold">Dashboard de Ensamblaje</h2>
            <p class="text-sm mb-2">Accede al área de ensamblaje para gestionar inventarios.</p>
            <a href="${pageContext.request.contextPath}/AREA_FABRICA/dashboardFabrica.jsp" class="underline">Ir a
                Ensamblaje</a>
        </div>

    </div>
</div>
</body>
</html>