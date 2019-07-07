<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Town editor</title>
</head>
<body>
    <a href="/">Back to homepage</a>
    <h2>Town editor</h2>
    <a href="/towns">Towns</a>
    <label> ${town.name}</label>
    <p></p>
    <c:if test="${isCreated.equals('false')}">
        <form method="GET" action="/towns/${town.id}/offices">
            <input type="hidden" name="townId" value="${town.id}">
            <input type="submit" value="Offices">
        </form>
    </c:if>
    <c:if test="${isRO.equals('false') and isCreated.equals('false')}">
        <form method="POST" action="/towns/${town.id}/delete">
            <input type="hidden" name="townId" value="${town.id}">
            <input type="submit" value="Delete">
        </form>
    </c:if>
    <c:choose>
        <c:when test="${isRO.equals('true')}">
            <form method="GET" action="/towns/${town.id}/edit">
                <label for="townName">Town name: </label>
                <input id="townName" type="text" name="townName" pattern="[a-zA-Z]{,25}" value="${town.name}" readonly><br>
                <input type="hidden" name="townId" value="${town.id}">
                <input type="submit" value="Edit">
            </form>
        </c:when>
        <c:otherwise>
            <form method="POST" action="${action}">
            <label for="townName">Town name: </label>
            <input id="townName" type="text" name="townName" pattern="[a-zA-Z]{,25}" value="${town.name}"><br>
            <input type="hidden" name="townId" value="${town.id}">
            <input type="submit" value="Save">
            </form>
        </c:otherwise>
    </c:choose>
</body>
</html>
