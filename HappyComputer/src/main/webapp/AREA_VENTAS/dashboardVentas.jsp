<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard Ventas</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.0.0/dist/tailwind.min.css" rel="stylesheet">
    <!-- Icono -->
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/sale.png">
</head>
<body class="bg-gray-100">
<div class="container mx-auto p-4">
    <h1 class="text-2xl font-bold mb-6 text-center">Área de Ventas</h1>
    <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        <div class="bg-blue-500 text-white p-4 rounded-lg shadow-lg text-center">
            <h2 class="text-lg font-semibold">Registrar Venta</h2>
            <p class="text-sm mb-2">Registra una nueva venta y genera un comprobante.</p>
            <a href="${pageContext.request.contextPath}/SvVenta" class="underline">Ir a Registrar</a>
        </div>

        <div class="bg-green-500 text-white p-4 rounded-lg shadow-lg text-center">
            <h2 class="text-lg font-semibold">Consultar Compras</h2>
            <p class="text-sm mb-2">Revisa el historial de compras realizadas por un cliente.</p>
            <a href="/HappyComputer_war/AREA_VENTAS/consultaCompras.jsp" class="underline">Ver Ventas</a>
        </div>

        <div class="bg-red-500 text-white p-4 rounded-lg shadow-lg text-center">
            <h2 class="text-lg font-semibold">Consultar Devoluciones</h2>
            <p class="text-sm mb-2">Consulta las devoluciones de productos realizadas.</p>
            <a href="/HappyComputer_war/AREA_VENTAS/consultarDevoluciones.jsp" class="underline">Ver Devoluciones</a>
        </div>

        <div class="bg-yellow-500 text-white p-4 rounded-lg shadow-lg text-center">
            <h2 class="text-lg font-semibold">Computadoras Disponibles</h2>
            <p class="text-sm mb-2">Consulta el inventario de computadoras en stock.</p>
            <a href="/HappyComputer_war/AREA_VENTAS/consultaComputadoras.jsp" class="underline">Ver Inventario</a>
        </div>

        <div class="bg-purple-500 text-white p-4 rounded-lg shadow-lg text-center">
            <h2 class="text-lg font-semibold">Detalle de Factura</h2>
            <p class="text-sm mb-2">Revisa los detalles de una factura específica.</p>
            <a href="${pageContext.request.contextPath}/SvFactura?action=listarVentas" class="underline">Ver Factura</a>
        </div>

        <div class="bg-pink-500 text-white p-4 rounded-lg shadow-lg text-center">
            <h2 class="text-lg font-semibold">Ventas del Día</h2>
            <p class="text-sm mb-2">Consulta el resumen de las ventas realizadas hoy.</p>
            <a href="${pageContext.request.contextPath}/SvVenta?action=ventaDelDia" class="underline">Ver Reporte</a>
        </div>

        <div class="bg-indigo-500 text-white p-4 rounded-lg shadow-lg text-center">
            <h2 class="text-lg font-semibold">Registrar Cliente</h2>
            <p class="text-sm mb-2">Registra un nuevo cliente en la base de datos.</p>
            <a href="${pageContext.request.contextPath}/AREA_VENTAS/registrarCliente.jsp" class="underline">Registrar
                Cliente</a>
        </div>
    </div>
</body>
</html>
