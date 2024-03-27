package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jdbc.QueryFunctions;
import logic.*;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "search", value = "/search")
public class SearchServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String recipeName = request.getParameter("recipe_name");
        String ingredient = request.getParameter("ingredient");
        List<Recipe> recipes;
        HttpSession session = request.getSession(true);

        if (Util.isNotNullOrEmpty(recipeName)) {
            recipes = QueryFunctions.getRecipes(recipeName);
        } else {
            recipes = QueryFunctions.getRecipes();
        }

        String ingredientName = request.getParameter("ingredient");
        if (Util.isNotNullOrEmpty(ingredientName)){
            recipes = QueryFunctions.recipesWithIngredient(ingredient);
        }

        String[] originFilters = request.getParameterValues("origin");

        if (originFilters != null) {
            recipes = Util.filterByOrigin(recipes, Util.processStrings(originFilters));
        }

        String[] seasonFilters = request.getParameterValues("season");

        if (seasonFilters != null) {
            recipes = Util.filterBySeason(recipes, Util.processStrings(seasonFilters));
        }

        String isVegan = request.getParameter("is_vegan");

        if (Util.isNotNullOrEmpty(isVegan)) {
            recipes = Util.filterOnlyVegan(recipes);
        }

        String username = String.valueOf(session.getAttribute("logged_user"));
        String boughtIngredients = request.getParameter("bought");

        if (Util.isNotNullOrEmpty(boughtIngredients)) {
            recipes = Util.filterByBoughtIngredients(recipes, username);
        }

        String expirationDate = request.getParameter("date");

        if (Util.isNotNullOrEmpty(expirationDate)) {
            recipes = Util.filterByDate(recipes, username, Date.valueOf(expirationDate));
        }

        session.setAttribute("recipes", recipes);
        response.sendRedirect("Search.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}