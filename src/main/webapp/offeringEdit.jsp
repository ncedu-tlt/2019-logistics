<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Offering editor</title>
</head>
<body>
    <a href="/">Back to homepage</a>
    <h2>Offering editor</h2>
    <p></p>
    <c:if test="${isRO.equals('false') and isCreated.equals('false')}">
        <form method="POST" action="/offerings/${offering.getOffice().getId()}/${offering.getProduct().getId()}/delete">
            <input type="hidden" name="officeId" value="${offering.getOffice().getId()}">
            <input type="hidden" name="productId" value="${offering.getProduct().getId()}">
            <input type="submit" value="Delete">
        </form>
    </c:if>

    <c:choose>
        <c:when test="${isRO.equals('true')}">
            <form method="POST" action="/offerings/${offering.getOffice().getId()}/${offering.getProduct().getId()}/edit">
                <label for="productId">Product name</label>
                <select id="productId" name="productId">
                    <option selected value="${offering.getProduct().getId()}">${offering.getProduct().getName()}</option>
                </select><br>
                <label for="price">Price</label>
                <input id="price" type="text" pattern="\d" name="price" value="${offering.getPrice()}" readonly><br>
                <input type="submit" value="Edit">
            </form>
        </c:when>
        <c:otherwise>
            <form method="POST" action="${action}">
                <label for="productId">Product name</label>
                <select id="productId" name="productId">
                    <c:if test="${offering.getProduct() == null}">
                        <option selected disabled>Select product</option>
                    </c:if>
                    <c:forEach items="${productsList}" var="product">
                        <c:choose>
                            <c:when test="${product.id == offering.getProduct().getId()}">
                                <option selected value="${product.id}">${product.name}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${product.id}">${product.name}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select><br>
                <label for="price">Price</label>
                <input id="price" type="text" pattern="\d{,10}" name="price" value="${offering.getPrice()}"><br>
                <c:choose>
                    <c:when test="${isCreated.equals('true')}">
                        <input type="hidden" name="officeId" value="${officeId}">
                    </c:when>
                    <c:otherwise>
                        <input type="hidden" name="officeId" value="${offering.getOffice().getId()}">
                    </c:otherwise>
                </c:choose>
                <input type="submit" value="Save">
            </form>
        </c:otherwise>
    </c:choose>
</body>
</html>
