<%--
  Created by IntelliJ IDEA.
  User: ishopjapan
  Date: 23/08/2022
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Management Application</title>
</head>
<body>
<center>
    <h1>User Management</h1>
    <h2>
        <a href="/users?action=create">Add New User</a>
    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Users</h2></caption>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>email</th>
            <th>country</th>
            <th>actions</th>
        </tr>
        <c:forEach var="user" items="${listUser}">
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.email}</td>
                <td>${user.country}</td>
                <td>
                    <a href="/users?action=edit&id=${user.id}">edit</a>
                    <a href="/users?action=delete&id=${user.id}">delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>
