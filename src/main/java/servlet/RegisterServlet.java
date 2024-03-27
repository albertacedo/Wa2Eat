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

@WebServlet(name = "register", value = "/register")
public class RegisterServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String crypto = GFG.encryptThisString(password);
        String repeatedPassword = request.getParameter("repeatedPassword");
        HttpSession session = request.getSession(true);

        if (password.equals(repeatedPassword)) {
            User user = new User(email, crypto);
            int status = QueryFunctions.registerUser(user);
            if (status > 0) {
                session.setAttribute("logged_user", user.getUsername());
                response.sendRedirect("index.jsp");
                List<Recipe> recipes = QueryFunctions.getRecipes();
                session.setAttribute("recipes", recipes);
                List<BoughtIngredient> bought_Ingredient = QueryFunctions.getBoughtIngredients(user.getUsername());
                session.setAttribute("bought_Ingredient", bought_Ingredient);
                //response.setHeader("refresh", "1;url=index.jsp");
            } else {
                RequestDispatcher rd = request.getRequestDispatcher("Register.jsp");
                request.setAttribute("err_msg", "The user is not valid!");
                rd.forward(request, response);
            }
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("Register.jsp");
            request.setAttribute("err_msg", "The passwords don't match!");
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