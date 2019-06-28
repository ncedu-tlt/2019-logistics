<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Town editor</title>
</head>
<body>
    <a href="/towns">Towns</a>
    <label> ${town.getName()}</label>
    <p></p>
    <c:if test="${isRO.equals('false') and isCreated.equals('false')}">
        <form method="POST" action="/towns/${town.getId()}/delete">
            <input type="hidden" name="townId" value="${town.getId()}">
            <input type="submit" value="Delete">
        </form>
        <form method="GET" action="/towns/${town.getId()}/offices">
            <input type="hidden" name="townId" value="${town.getId()}">
            <input type="submit" value="Offices">
        </form>
    </c:if>
    <c:choose>
        <c:when test="${isRO.equals('true')}">
            <form method="GET" action="/towns/${town.getId()}/edit">
                <label for="townName">Town name: </label>
                <input id="townName" type="text" name="townName" pattern="[a-zA-z]{,25}" value="${town.getName()}" readonly><br>
                <input type="hidden" name="townId" value="${town.getId()}">
                <input type="submit" value="Edit">
            </form>
        </c:when>
        <c:otherwise>
            <form method="POST" action="${action}">
            <label for="townName">Town name: </label>
            <input id="townName" type="text" name="townName" pattern="[a-zA-z]{,25}" value="${town.getName()}"><br>
            <input type="hidden" name="townId" value="${town.getId()}">
            <input type="submit" value="Save">
            </form>
        </c:otherwise>
    </c:choose>
</body>
</html>
