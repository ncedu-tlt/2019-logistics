<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src='https://code.jquery.com/jquery-3.4.1.min.js'></script>
    <script src='https://code.jquery.com/jquery-3.4.1.slim.min.js'></script>
    <script src="/js/checkLogic.js"></script>
    <script>
        window.onload = main
    </script>
    <title>Cheapest</title>
</head>
<body>
    <a href="/">Back to homepage</a>
    <h2>Search the cheapest offering</h2>

    <form method="POST" action="/logistic">
        <label for="town">Destination town:</label>
        <select class="jsTown" id="town" name="townId" style="margin-left: 5px">
            <option selected disabled>Select town</option>
            <c:forEach items="${towns}" var="town">
                <option value="${town.id}">${town.name}</option>
            </c:forEach>
        </select>

        <p></p>

        <label for="product">Product:</label>
        <select class="jsProduct" id="product" name="productId" style="margin-left: 5px">
            <option selected disabled>Select product</option>
            <c:forEach items="${products}" var="product">
                <option value="${product.id}">${product.name}</option>
            </c:forEach>
        </select>

        <p></p>

        <input class="jsSearch" type="submit" value="Search">
    </form>

</body>
</html>
