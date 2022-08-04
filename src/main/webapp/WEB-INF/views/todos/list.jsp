<%--
  Created by IntelliJ IDEA.
  User: ash
  Date: 3/8/22
  Time: 19:34
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="../layouts/header.jsp"%>


<c:if test="${todos.size()==0}">
    <h1 class="text-3xl font-bold mt-[50%] text-center">No Todos added, Try adding some</h1>
</c:if>

<div class="flex flex-wrap gap-4 justify-center">
    <c:forEach var="todo" items="${todos}">
        <c:url var="editLink" value="todos/edit">
            <c:param name="id" value="${todo.id}"/>
        </c:url>
        <c:url var="deleteLink" value="todos/delete">
            <c:param name="id" value="${todo.id}"/>
        </c:url>
        <div class="border-2 border-gray-600 rounded-xl min-w-[500px] min-h-[150px] p-3 shadow-xl">
            <div class="flex items-center justify-between">
                <h1 class="text-4xl font-bold mt-3 w-full">${todo.name}</h1>
                <div class="flex items-center">
                    <a href="${editLink}">
                        <%@include file="../SVGs/edit.jsp"%>
                    </a>
                    <a href="${deleteLink}">
                        <%@include file="../SVGs/delete.jsp"%>
                    </a>
                </div>
            </div>
            <c:if test="${!action.equals(actionAdd)}">
                <a
                        class="text-blue-900 hover:text-blue-700 transition-colors cursor-pointer"
                        href="${pageContext.request.contextPath}/todos?action=add&id=${todo.id}"
                >
                    Add SubTask
                </a>
            </c:if>
            <c:if test="${action!=null && action.equals(actionAdd) && id!=null && id==todo.id}">
                <form:form
                        action="${pageContext.request.contextPath}/subtask/create?todo_id=${todo.id}"
                        method="POST"
                        modelAttribute="newSubtask"
                        cssClass="flex items-center gap-2 mt-1"
                >
                    <form:input class="w-full border-2 p-1 border-gray-300 rounded" path="subTaskName" placeholder="New Subtask" />
                    <div class="flex items-center gap-2">
                        <input
                                class="text-white cursor-pointer rounded p-1 border-2 border-green-700 bg-green-600 hover:bg-green-700 transition-colors"
                                type="submit" value="Add"/>
                        <a
                                class="text-white cursor-pointer rounded p-1 border-2 border-red-700 bg-red-600 hover:bg-red-700 transition-colors"
                                href="${pageContext.request.contextPath}/todos/">Cancel</a>
                    </div>
                </form:form>
            </c:if>
            <div class="mt-4">
                <c:forEach var="subtask" items="${todo.subTasks}" >
                    <div>
                        <c:choose>
                            <c:when test="${action!=null && action.equals(actionEdit) && id!=null && id==subtask.id}">
                                <form
                                        action="${pageContext.request.contextPath}/subtask/update?id=${editingSubTask.id}"
                                        method="POST"
                                        class="flex items-center gap-2"
                                >
                                    <input hidden name="todo_id" value="${editingSubTask.todo.id}" readonly/>
                                    <input class="w-full border-2 p-1 border-gray-300 rounded" name="subtaskName" value="${editingSubTask.subTaskName}"/>
                                    <input
                                            class="text-white cursor-pointer rounded p-1 border-2 border-green-700 bg-green-600 hover:bg-green-700 transition-colors"
                                            type="submit" value="Update"/>
                                    <a
                                            class="text-white cursor-pointer rounded p-1 border-2 border-red-700 bg-red-600 hover:bg-red-700 transition-colors"
                                            href="${pageContext.request.contextPath}/todos/">
                                        Cancel
                                    </a>
                                </form>
                            </c:when>
                            <c:otherwise>
                                <div class="flex justify-between items-center">
                                    <h1 class="w-full">${subtask.subTaskName}</h1>
                                    <div class="flex items-center">
                                        <a href="${pageContext.request.contextPath}/todos?action=edit&id=${subtask.id}">
                                            <%@include file="../SVGs/edit.jsp"%>
                                        </a>
                                        <a href="${pageContext.request.contextPath}/subtask/delete?id=${subtask.id}">
                                            <%@include file="../SVGs/delete.jsp"%>
                                        </a>
                                    </div>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </c:forEach>
            </div>
        </div>
    </c:forEach>
</div>
<%@include file="../layouts/footer.jsp"%>