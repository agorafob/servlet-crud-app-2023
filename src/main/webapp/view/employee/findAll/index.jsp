<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employees</title>
</head>
<body>
<h2>Список работников</h2>
<%--<% Object employees = request.getAttribute("employees"); %>--%>
<%--<%= employees%>--%>

<p><a href='<c:url value="/employees/create" />'>Добавление нового сотрудника</a></p>
<table>
    <tr>
        <th>Подразделение</th>
        <th>ФИО</th>
        <th>Зарплата</th>
        <th>Начальник</th>
        <th></th>
    </tr>
    <c:forEach var="employee" items="${employees}">
        <tr>
            <td>${employee.department}</td>
            <td>${employee.name}</td>
            <td>${employee.salary}</td>
            <td>
                <c:if test="${employee.chiefId == null}">
                    *
                </c:if>
            </td>
            <td>
                <a href='<c:url value="/employees/edit?id=${employee.id}" />'>Edit employee</a> |
                <form method="post" action='<c:url value="/employees/delete" />' style="display:inline;">
                    <input type="hidden" name="id" value="${employee.id}">
                    <input type="submit" value="Delete employee">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
