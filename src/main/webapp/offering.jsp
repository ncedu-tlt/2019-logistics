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
                <c:choose>
                    <c:when test="${prodIdServ == product.id}">
                        <option selected value="${product.id}">${product.name}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${product.id}">${product.name}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>
        <p></p>
        <select class="jsTownSelect" name="townId">
            <option selected disabled>Select town</option>
            <c:forEach items="${towns}" var="town">
                <c:choose>
                    <c:when test="${townIdServ == town.id}">
                        <option selected value="${town.id}">${town.name}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${town.id}">${town.name}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>

        <select class="jsOfficeSelect" id="officeSelect" name="officeId" style="display: none; margin-left: 30px">
        </select><br><br>

        <input type="submit" value="Search">

        <c:if test="${isGet.equals('true')}">
            <table border="1">
                <theadx>
                    <tr>
                        <th>Town name</th>
                        <th>Office phone</th>
                        <th>Product name</th>
                        <th>Price</th>
                    </tr>
                </theadx>
                <tbody>
                    <c:forEach items="${offers}" var="offer">
                        <tr>
                            <td>${offer.office.town.name}</td>
                            <td>${offer.office.phone}</td>
                            <td>${offer.product.name}</td>
                            <td>${offer.price}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>

    </form>


</body>
</html>
