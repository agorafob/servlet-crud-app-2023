<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Departments</title>
</head>
<body>
<h2>Список департаментов</h2>
<%--<% Object department = request.getAttribute("departments"); %>--%>
<%--<%= department%>--%>

<p><a href='<c:url value="/departments/create" />'>Добавление нового департамента</a></p>
<table>
    <tr>
        <th>Номер департамента</th>
        <th>Название департамента</th>
        <th></th>
    </tr>
    <c:forEach var="department" items="${departments}">
        <tr>
            <td>${department.id}</td>
            <td>${department.name}</td>

            <td>
                <a href='<c:url value="/departments/edit?id=${department.id}" />'>Edit department</a> |
                <form method="post" action='<c:url value="/departments/delete" />' style="display:inline;">
                    <input type="hidden" name="id" value="${department.id}">
                    <input type="submit" value="Delete department">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
