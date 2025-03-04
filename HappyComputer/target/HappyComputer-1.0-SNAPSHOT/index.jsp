<%-- 
    Document   : index
    Created on : Feb 28, 2025, 9:45:55 PM
    Author     : luluwalilith
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.png">
    <title>Login</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="flex items-center justify-center h-screen bg-gray-100">
    <div class="w-full max-w-md p-8 space-y-4 bg-white rounded-lg shadow-md">
        <h2 class="text-2xl font-bold text-center">Iniciar Sesión</h2>
        <form action="LoginController" method="post" class="space-y-4">
            <div>
                <label for="usuario" class="block text-sm font-medium text-gray-700">Usuario</label>
                <input type="text" id="usuario" name="usuario" required
                    class="w-full px-4 py-2 mt-1 border rounded-md focus:ring focus:ring-blue-300">
            </div>
            <div>
                <label for="password" class="block text-sm font-medium text-gray-700">Contraseña</label>
                <input type="password" id="password" name="password" required
                    class="w-full px-4 py-2 mt-1 border rounded-md focus:ring focus:ring-blue-300">
            </div>
            <button type="submit"
                class="w-full px-4 py-2 font-bold text-white bg-blue-600 rounded-md hover:bg-blue-700">
                Iniciar Sesión
            </button>
        </form>
    </div>
</body>
</html>

