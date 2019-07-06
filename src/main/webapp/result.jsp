<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <a href="/">Back to homepage</a><br>
    <a href="/logistic">Back to previous page</a><br><br>

    <h2>Result</h2>

    <c:if test="${isGet.equals('true')}">
        <form>
            <label>Your town: ${yourTown}</label><br>
            <label>Product will be shipped from: ${minTown.name}</label><br>
            <label>Office phone: ${minOffer.office.phone}</label><br>
            <label>Distance: ${distance}</label><br>
            <label>Product price: ${minOffer.price}</label><br>
            <label>Total price: ${totalPrice}</label><br><br>

            <label style="font-size: 90%"><i>List of roads to your town</i></label>
            <table border="1">
                <thead>
                <tr>
                    <th>First town</th>
                    <th>Second town</th>
                    <th>Distance</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${path}" var="road">
                    <tr>
                        <td>${road.leftTown.name}</td>
                        <td>${road.rightTown.name}</td>
                        <td>${road.distance}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </form>
    </c:if>

    <c:if test="${isGet.equals('false')}">
        <label>Sorry, there is no possibility to deliver the product.</label>
    </c:if>
</body>
</html>
