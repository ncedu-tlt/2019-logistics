<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js'></script>
    <script src='https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.0/jquery-ui.min.js'></script>
    <script src="/js/getOfficesByTownId.js"></script>
    <script>
        window.onload = main
    </script>
    <title>Search offering</title>
</head>
<body>

    <a href="/">Back to homepage</a>
    <h2>Search offering</h2>

    <form method="POST" action="/SearchOfferingsServlet">
        <select id="productSelect" name="productId">
            <option selected disabled>Select product</option>
            <c:forEach items="${products}" var="product">
                <option value="${product.id}">${product.getName()}</option>
            </c:forEach>
        </select>
        <p></p>
        <select class="jsTownSelect" name="townId">
            <option selected disabled>Select town</option>
            <c:forEach items="${towns}" var="town">
                <option value="${town.id}">${town.name}</option>
            </c:forEach>
        </select>

        <select class="jsOfficeSelect" id="officeSelect" name="officeId" style="display: none; margin-left: 30px">
        </select><br><br>

        <input type="submit" value="Search">
    </form>


</body>
</html>
