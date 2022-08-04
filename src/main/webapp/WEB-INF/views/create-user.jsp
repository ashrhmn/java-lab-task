<%--
  Created by IntelliJ IDEA.
  User: ash
  Date: 3/8/22
  Time: 10:36
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="layouts/header.jsp"%>
<form:form action="${pageContext.request.contextPath}/users/create" modelAttribute="user" method="POST">
    <table>
        <tbody>
        <tr>
            <td><label>Name:</label></td>
            <td><form:input path="name"/></td>
            <td class="text-red-500"><form:errors path="name"/></td>
        </tr>
        <tr>
            <td><label>Address:</label></td>
            <td><form:input path="address"/></td>
            <td class="text-red-500"><form:errors path="address"/></td>
        </tr>
        <tr>
            <td><label></label></td>
            <td><input type="submit" value="Save"/></td>
        </tr>
        </tbody>
    </table>
</form:form>
<%@include file="layouts/footer.jsp"%>