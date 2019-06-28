<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Towns</title>
</head>
<a href="/">Back to homepage</a>
<body>
    <h2>List of towns</h2>
    <form method="GET" action="/CreateTownServlet">
        <input type="submit" value="Create new town">
    </form>
    <table border="1">
        <theadx>
            <tr>
                <th>Name</th>
            </tr>
        </theadx>
        <tbody>
            <c:forEach items="${townsList}" var="town">
                <tr>
                    <td><a href="/towns/${town.getId()}">${town.getName()}</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
