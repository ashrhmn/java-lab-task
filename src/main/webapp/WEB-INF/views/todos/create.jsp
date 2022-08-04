<%--
  Created by IntelliJ IDEA.
  User: ash
  Date: 3/8/22
  Time: 21:49
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../layouts/header.jsp"%>

<form:form
        action="${pageContext.request.contextPath}/todos/create"
        method="POST"
        modelAttribute="todo"
        cssClass="flex items-stretch text-3xl gap-4 mt-[50%]"
>
    <div class="w-full flex items-center border-2 border-gray-300 rounded">
        <form:input cssClass="w-full focus:outline-none p-2" path="name" placeholder="New Task Name"/>
        <form:errors cssClass="min-w-[220px] text-red-500" path="name"/>
    </div>
    <input class="w-28 cursor-pointer bg-blue-600 text-white hover:bg-blue-700 transition-colors p-1 rounded" type="submit" value="Save"/>
</form:form>

<%@include file="../layouts/footer.jsp"%>