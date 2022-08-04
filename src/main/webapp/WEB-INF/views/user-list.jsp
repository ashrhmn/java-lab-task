<%--
  Created by IntelliJ IDEA.
  User: ash
  Date: 3/8/22
  Time: 09:24
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="layouts/header.jsp"%>
    <h1 class="text-center text-4xl font-bold">Users List</h1>
<table class="w-full mt-10">
    <thead>
    <tr>
        <th class="border-2 border-gray-300 text-center py-1">ID</th>
        <th class="border-2 border-gray-300 text-center py-1">Name</th>
        <th class="border-2 border-gray-300 text-center py-1">Address</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="user" items="${users}">
        <tr class="border-2 border-gray-300 even:bg-gray-100">
            <td class="border-2 border-gray-300 text-center py-1">
                    ${user.id}
            </td>
            <td class="border-2 border-gray-300 text-center py-1">
                    ${user.name}
            </td>
            <td class="border-2 border-gray-300 text-center py-1">
                    ${user.address}
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<%@include file="layouts/footer.jsp"%>