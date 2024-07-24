<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 02.07.2024
  Time: 20:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>mealForm</title>
</head>
<body>
<header>
    <h3><a href="${pageContext.request.contextPath}/index.html">Home</a></h3>
</header>

<main>
   <section>
<%--       <jsp:useBean id="meal" type="ru.dmitrii_egorov.manager.model.MealTo"/>--%>

       <form method="post" action="meals">
           <input type="hidden" name="id" value="${meal.id}">
           <h3><a href="${pageContext.request.contextPath}/index.html">Home</a></h3>
           <input type="hidden" name="id" value="${meal.id}">
           <div class="form-group">
               <label for="dateTime">DateTime:</label>
               <input type="datetime-local" id="dateTime" value="${meal.dateTime}" name="dateTime" required>
           </div>
           <div class="form-group">
               <label for="description">Description:</label>
               <input type="text" id="description" value="${meal.description}" size="40" name="description" required>
           </div>
           <div class="form-group">
               <label for="calories">Calories:</label>
               <input type="number" id="calories" value="${meal.calories}" name="calories" required>
           </div>
           <div class="form-buttons">
               <button type="submit">Save</button>
               <button onclick="window.history.back()" type="button">Cancel</button>
           </div>
       </form>
   </section>
</main>
</body>
</html>
