<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Products</title>
</head>
<a href="/">Back to homepage</a>
<body>
    <h2>List of products</h2>
    <table border="1">
        <theadx>
            <tr>
                <th>Product ID</th>
                <th>Name</th>
            </tr>
        </theadx>
        <tbody>
            <c:forEach items="${productList}" var="product">
                <tr>
                    <td>${product.getId()}</td>
                    <td>${product.getName()}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
