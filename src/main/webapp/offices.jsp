<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Offices</title>
</head>
<body>
    <h2>List of offices</h2>
    <a href="/">Back to homepage</a>
    <label>Town: ${townName}</label>
    <p></p>
    <form method="GET" action="/CreateOfficeServlet">
        <input type="submit" value="Create new office">
    </form>
    <table border="1">
        <theadx>
            <tr>
                <th>Phone</th>
            </tr>
        </theadx>
        <tbody>
            <c:forEach items="${officesById}" var="office">
                <tr>
                    <td><a href="/offices/${office.getId()}">${office.getPhone()}</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
