package servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jdbc.QueryFunctions;
import logic.BoughtIngredient;
import logic.GFG;
import logic.Recipe;
import logic.User;

import java.io.IOException;
import java.util.List;


@WebServlet(name = "login", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("email");
        String password = request.getParameter("password");
        String crypto = GFG.encryptThisString(password);
        User user = new User(username, crypto);
        boolean checkUser = QueryFunctions.checkUser(user);
        HttpSession session = request.getSession(true);

        if (checkUser) {
            session.setAttribute("logged_user", user.getUsername());
            response.sendRedirect("index.jsp");
            List<Recipe> recipes = QueryFunctions.getRecipes();
            session.setAttribute("recipes", recipes);
            List<BoughtIngredient> bought_Ingredient = QueryFunctions.getBoughtIngredients(user.getUsername());
            session.setAttribute("bought_Ingredient", bought_Ingredient);
            //response.setHeader("refresh", "1;url=index.jsp");

        } else {
            RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
            request.setAttribute("err_msg", "Invalid user or password");
            rd.forward(request, response);

        }
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