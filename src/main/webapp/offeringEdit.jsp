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
        <form method="POST" action="/offerings/${offering.office.id}/${offering.getProduct().getId()}/delete">
            <input type="hidden" name="officeId" value="${offering.office.id}">
            <input type="hidden" name="productId" value="${offering.product.id}">
            <input type="submit" value="Delete">
        </form>
    </c:if>

    <c:choose>
        <c:when test="${isRO.equals('true')}">
            <form method="POST" action="/offerings/${offering.office.id}/${offering.product.id}/edit">
                <label for="productId">Product name</label>
                <select id="productId" name="productId">
                    <option selected value="${offering.product.id}">${offering.product.name}</option>
                </select><br><br>
                <label for="price">Price</label>
                <input id="price" type="text" pattern="\d" name="price" value="${offering.price}" readonly><br><br>
                <input type="submit" value="Edit">
            </form>
        </c:when>
        <c:otherwise>
            <form method="POST" action="${action}">
                <label for="productId">Product name</label>
                <select id="productId" name="productId">
                    <c:if test="${offering.product == null}">
                        <option selected disabled>Select product</option>
                    </c:if>
                    <c:forEach items="${productsList}" var="product">
                        <c:choose>
                            <c:when test="${product.id == offering.product.id}">
                                <option selected value="${product.id}">${product.name}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${product.id}">${product.name}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select><br>
                <label for="price">Price</label>
                <input id="price" type="text" pattern="\d{2,10}(\.\d{1,3})?" name="price" value="${offering.price}"><br>
                <c:choose>
                    <c:when test="${isCreated.equals('true')}">
                        <input type="hidden" name="officeId" value="${officeId}">
                    </c:when>
                    <c:otherwise>
                        <input type="hidden" name="officeId" value="${offering.office.id}">
                    </c:otherwise>
                </c:choose>
                <input type="submit" value="Save">
            </form>
        </c:otherwise>
    </c:choose>
</body>
</html>
