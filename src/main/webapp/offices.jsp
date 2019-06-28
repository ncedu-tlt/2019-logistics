<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Offices</title>
</head>
<body>
    <h2>Offices</h2>
    <a href="/">Back to homepage</a>
    <p>Town: ${townName}</p>
    <table border="1">
        <theadx>
            <tr>
                <th>Phone</th>
            </tr>
        </theadx>
        <tbody>
            <c:forEach items="${officesById}" var="office">
                <tr>
                    <td>${office.getPhone()}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
