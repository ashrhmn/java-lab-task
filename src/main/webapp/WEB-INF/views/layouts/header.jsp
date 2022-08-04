<%--
  Created by IntelliJ IDEA.
  User: ash
  Date: 3/8/22
  Time: 18:12
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>JAVA Lab Task</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body>

<nav class="fixed top-0 left-0 right-0 flex justify-end gap-4 text-xl font-medium p-4 bg-gray-700 text-white shadow-2xl">
    <a class="text-gray-300 hover:text-white transition-colors" href="${pageContext.request.contextPath}/users/list">User List</a>
    <a class="text-gray-300 hover:text-white transition-colors" href="${pageContext.request.contextPath}/users/create">Create User</a>
    <a class="text-gray-300 hover:text-white transition-colors" href="${pageContext.request.contextPath}/todos">Todo List</a>
    <a class="text-gray-300 hover:text-white transition-colors" href="${pageContext.request.contextPath}/todos/create">Create Todo</a>
</nav>

<div class="mt-20 max-w-5xl mx-auto">