<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit Department</title>
</head>
<body>
<h2>Обновление данных департамента</h2>

<%--<% Object department = request.getAttribute("department"); %>--%>
<%--<%= department%>--%>

<%--<h1>Department</h1>--%>
<%--<c:out value="${department}"/>--%>

<form method="post">
    <input type="hidden" value="${department.id}" name="id" />
    <label>Name</label><br>
    <input name="name" value="${department.name}" /><br><br>
    <input type="submit" value="Send" />
</form>

</body>
</html>
