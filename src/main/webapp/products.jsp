<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Products</title>
</head>
<body>
    <a href="/">Back to homepage</a>
    <h2>List of products</h2>
    <form method="GET" action="/CreateProductServlet">
        <input type="submit" value="Create new product">
    </form>
    <table border="1">
        <theadx>
            <tr>
                <th>Name</th>
            </tr>
        </theadx>
        <tbody>
            <c:forEach items="${productList}" var="product">
                <tr>
                    <td><a href="/products/${product.id}">${product.name}</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
