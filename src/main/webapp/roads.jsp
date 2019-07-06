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
    <title>Search roads</title>
</head>
<body>
    <a href="/">Back to homepage</a>
    <h2>Search roads</h2>

    <form method="POST" action="/roads/create">
        <input type="submit" value="Create new road">
    </form>

    <form method="POST" action="/roads/search">
        <select class="jsLeft" name="leftId">
            <option selected disabled>Select first town</option>
            <c:forEach items="${towns}" var="town">
                <c:choose>
                    <c:when test="${leftId == town.id}">
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
            <option selected disabled>Select second town</option>
            <c:forEach items="${towns}" var="town">
                <c:choose>
                    <c:when test="${rightId == town.id}">
                        <option selected value="${town.id}">${town.name}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${town.id}">${town.name}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>

        <p></p>

        <input class="jsSave" type="submit" value="Search">
    </form>

    <c:if test="${isGet.equals('true')}">
        <label style="font-size: 90%"><i>click on distance for editing road</i></label>
        <table border="1">
            <thead>
                <tr>
                    <th>First town</th>
                    <th>Second town</th>
                    <th>Distance (km)</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${foundRoads}" var="road">
                    <tr>
                        <td>${road.leftTown.name}</td>
                        <td>${road.rightTown.name}</td>
                        <td><a href="/roads/${road.leftTown.id}/${road.rightTown.id}/edit">${road.distance}</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>

    <c:if test="${isGet.equals('false')}">
        <label>Road doesn't exist</label>
    </c:if>

</body>
</html>
