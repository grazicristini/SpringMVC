<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8"/>
    <title>Jogos</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
    <h1>Jogos</h1>
        <a href="/livro/insert" class="btn btn-primary">Novo Jogo</a>
        <table class="table">
            <tr>
                <th>Id</th>
                <th>Título</th>
                <th>Ano de Publicação</th>
                <th>&nbsp;</th>
            </tr>
            <c:forEach var="item" items="${jogos}">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.titulo}</td>
                    <td>${item.anoDeLancamento}</td>
                    <td>
                        <a href="/livro/update?id=${item.id}" class="btn btn-warning">Editar</a>
                        <a href="/livro/delete?id=${item.id}" class="btn btn-danger">Editar</a>
                    </td>                
                </tr>
            </c:forEach>
        </table>        
    </div>
</body>
</html>