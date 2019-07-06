<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Offices</title>
</head>
<body>
    <a href="/">Back to homepage</a>
    <h2>List of offices</h2>
    <label>Town: ${townName}</label>
    <p></p>
    <form method="GET" action="/CreateOfficeServlet">
        <input type="hidden" name="townId" value="${townId}">
        <input type="submit" value="Create new office">
    </form>
    <table border="1">
        <theadx>
            <tr>
                <th>Office phone</th>
            </tr>
        </theadx>
        <tbody>
            <c:forEach items="${officesById}" var="office">
                <tr>
                    <td><a href="/offices/${office.id}">${office.phone}</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
