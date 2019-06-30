<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Product editor</title>
</head>
<body>
    <a href="/">Back to homepage</a>
    <h2>Product editor</h2>
    <a href="/products">Products</a>
    <label> ${product.getName()}</label>
    <p></p>
    <c:if test="${isRO.equals('false') and isCreated.equals('false')}">
        <form method="POST" action="/products/${product.getId()}/delete">
            <input type="hidden" name="productId" value="${product.getId()}">
            <input type="submit" value="Delete">
        </form>
    </c:if>
    <c:choose>
        <c:when test="${isRO.equals('true')}">
            <form method="GET" action="/products/${product.getId()}/edit">
                <label for="productName">Product name: </label>
                <input id="productName" type="text" value="${product.getName()}" readonly><br>
                <input type="hidden" name="productId" value="${product.getId()}"><br>
                <input type="submit" value="Edit">
            </form>
        </c:when>
        <c:otherwise>
            <form method="POST" action="${action}">
                <label for="productName">Product name: </label>
                <input id="productName" type="text" name="productName" pattern="[a-zA-z]{,25}" value="${product.getName()}"><br>
                <input type="hidden" name="productName" value="${product.getId()}">
                <input type="submit" value="Save">
            </form>
        </c:otherwise>
    </c:choose>
</body>
</html>
