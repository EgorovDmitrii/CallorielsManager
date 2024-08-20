<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<h3><a href="${pageContext.request.contextPath}/index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<br>
<br>
<a href="meals?action=create">Add Meal</a>

<section class="table-container">

        <table>
            <thead>
            <tr>Date</tr>
            <tr>Description</tr>
            <tr>Calories</tr>
            <tr></tr>
            <tr></tr>
            </thead>

            <tbody>
                <c:forEach var="meal" items="${requestScope.meals}">
                    <jsp:useBean id="meal" type="ru.dmitrii_egorov.manager.model.MealTo"/>

                    <tr class="${meal.excess ? 'excess' : 'normal'}">
                        <td>${meal.dateTime}</td>
                        <td>${meal.descrition}</td>
                        <td>${meal.calories}</td>
                        <td>
                            <a href="meals?action=update&id=${meal.id}">Update</a>
                            <a href="meals?action=delete&id=${meal.id}">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

</section>
</body>
</html>