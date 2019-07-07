<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Office editor</title>
</head>
<body>
    <a href="/">Back to homepage</a>
    <h2>Office editor</h2>
    <p></p>
    <c:if test="${isRO.equals('false') and isCreated.equals('false')}">
       <form method="POST" action="/offices/${office.id}/delete">
           <input type="hidden" name="officeId" value="${office.id}">
           <input type="submit" value="Delete">
       </form>
    </c:if>
    <c:choose>
        <c:when test="${isRO.equals('true')}">
            <form method="GET" action="/offices/${office.id}/edit">
                <label for="phone">Phone number</label>
                <input id="phone" type="text" value="${office.phone}" readonly><br>
                <label for="townName">Town name</label>
                <input id="townName" type="text" value="${office.town.name}" readonly><br>
                <input type="hidden" value="${office.id}" name="officeId">
                <input type="submit" value="Edit">
            </form>
        </c:when>
        <c:otherwise>
            <form method="GET" action="/offerings/create">
                <input type="hidden" name="officeId" value="${office.id}">
                <input type="submit" value="Create new offering">
            </form>
            <form method="POST" action="${action}">
                <label for="phone">Phone number</label>
                <input id="phone" type="text" pattern="\d{6}" name="phoneNumber" value="${office.phone}"><br>
                <label for="townId">Town name</label>
                <select id="townId" name="townId">
                    <option disabled>Select town</option>
                    <c:forEach items="${townsList}" var="town">
                        <c:choose>
                            <c:when test="${town.id == office.town.id}">
                                <option selected value="${town.id}">${town.name}</option>
                            </c:when>
                            <c:when test="${town.id == townId}">
                                <option selected value="${town.id}">${town.name}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${town.id}">${town.name}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select><br>
                <input type="hidden" value="${office.id}" name="officeId">
                <input type="submit" value="Save">
            </form>
        </c:otherwise>
    </c:choose>
</body>
</html>
