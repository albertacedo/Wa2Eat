<%--
  Created by IntelliJ IDEA.
  User: pablo
  Date: 19/10/2022
  Time: 14:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ page import="jdbc.QueryFunctions" %>
<%@ page import="logic.Ingredient" %>
<%@ page import="logic.Util" %>
<%@ page import="java.util.List" %>
<%@ page import="logic.Recipe" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Insert title here</title>
</head>
<body>

<%
    String ingredientName = request.getParameter("ingredient_name");
    String recipeName = request.getParameter("recipe_name");
    if (Util.isNotNullOrEmpty(ingredientName)) {
        Ingredient ingredient = QueryFunctions.getIngredientData(ingredientName);

        if (ingredient == null) {
            out.print("This ingredient does not exist");
        } else {
            out.print(ingredient.toString());
        }
    }

    out.print("\n");

    if (Util.isNotNullOrEmpty(recipeName)) {
        List<Recipe> recipes = QueryFunctions.getRecipes(recipeName);

        if (recipes.isEmpty()) {
            out.print("There is no recipe with this name");
        } else {
            for (Recipe recipe : recipes) {
                out.print(recipe.toString() + "\n");
            }
        }
    }
%>
</body>
</html>
