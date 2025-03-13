<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Usuarios</title>
</head>
<body>
<h1>Lista de Usuarios</h1>
<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Usuario</th>
        <th>Rol</th>
        <th>Estado</th>
        <th>Acciones</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="usuario" items="${usuarios}">
        <tr>
            <td>${usuario.id}</td>
            <td>${usuario.usuario}</td>
            <td>${usuario.idRol}</td>
            <td>${usuario.estado ? 'Activo' : 'Inactivo'}</td>
            <td>
                <a href="editarUsuario.jsp?id=${usuario.id}">Editar</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>