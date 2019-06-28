<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Editing office...</title>
</head>
<body>
    <form method="POST" action="${action}">
        <label for="phone">Phone number</label>
        <input id="phone" type="text" pattern="\d{6}" name="phoneNumber" value="${office.getPhone()}"><br>
        <label for="townId">Town name</label>
        <select id="townId" name="townId">
            <c:if test="${office.getTown() == null}">
                <option selected disabled>Select town</option>
            </c:if>
            <c:forEach items="${townsList}" var="town">
                <c:choose>
                    <c:when test="${town.id == office.getTown().getId()}">
                        <option selected value="${town.id}">${town.name}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${town.id}">${town.name}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select><br>
        <input type="hidden" value="${office.getId()}" name="officeId">
        <input type="submit" value="Save">
    </form>
</body>
</html>
