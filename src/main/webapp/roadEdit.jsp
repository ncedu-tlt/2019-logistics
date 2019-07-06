<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js'></script>
    <script src='https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.0/jquery-ui.min.js'></script>
    <script src="/js/checkRoad.js"></script>
    <script>
        window.onload = main
    </script>
    <title>Road editor</title>
</head>
<body>
    <a href="/">Back to homepage</a>
    <h2>Road editor</h2>

    <c:if test="${isCreated.equals('false')}">
        <form method="POST" action="/roads/${road.leftTown.id}/${road.rightTown.id}/delete">
            <input type="submit" value="Delete">
        </form>
    </c:if>

    <form method="POST" action="${action}">
        <select class="jsLeft" name="leftId">
            <option selected disabled>Select town</option>
            <c:forEach items="${towns}" var="town">
                <c:choose>
                    <c:when test="${road.leftTown.id == town.id}">
                        <option selected value="${town.id}">${town.name}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${town.id}">${town.name}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>

        <p></p>

        <select class="jsRight" name="rightId">
            <option selected disabled>Select town</option>
            <c:forEach items="${towns}" var="town">
                <c:choose>
                    <c:when test="${road.rightTown.id == town.id}">
                        <option selected value="${town.id}">${town.name}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${town.id}">${town.name}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>

        <p></p>
        <label for="distance">Distance: </label>
        <input id="distance" type="text" name="distance" value="${road.distance}" pattern="\d{1,5}.?\d{1,3}?"><br>

        <input class="jsSave" type="submit" value="Save">
    </form>

    <c:if test="${errorEdit.equals('true')}">
        <form method="POST" action="/roads/create">
            <label>This road doesn't exist. Do you want to create?</label>
            <input type="hidden" name="leftId" value="${road.leftTown.id}">
            <input type="hidden" name="rightId" value="${road.rightTown.id}">
            <input type="hidden" name="distance" value="${road.distance}">
            <input type="submit" value="Create" style="margin-left: 15px">
        </form>
    </c:if>

    <c:if test="${errorCreate.equals('true')}">
        <form method="POST" action="/roads/${road.leftTown.id}/${road.rightTown.id}/edit">
            <label>This road exists. Do you want to update?</label>
            <input type="hidden" name="leftId" value="${road.leftTown.id}">
            <input type="hidden" name="rightId" value="${road.rightTown.id}">
            <input type="hidden" name="distance" value="${road.distance}">
            <input type="submit" value="Update" style="margin-left: 15px">
        </form>
    </c:if>

</body>
</html>
