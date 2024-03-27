package servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jdbc.QueryFunctions;
import logic.BoughtIngredient;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import static jdbc.QueryFunctions.*;

@WebServlet(name = "delete_ingredient", value = "/delete_ingredient")
public class DeleteIngredientServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String ingredient = request.getParameter("name");
        Date date = Date.valueOf(request.getParameter("date"));
        String username = String.valueOf(session.getAttribute("logged_user"));
        int success = deleteBI(username, date, ingredient);
        List<BoughtIngredient> bought_Ingredient = QueryFunctions.getBoughtIngredients(username);
        session.setAttribute("bought_Ingredient", bought_Ingredient);
        RequestDispatcher rd = request.getRequestDispatcher("ingredients.jsp");
        request.setAttribute("err_msg", null);
        request.setAttribute("success_msg", null);

        if (success != 0) {
            request.setAttribute("success_msg_2", "Ingredient deleted successfully!");
        } else {
            request.setAttribute("err_msg_2", "Error deleting this ingredient");
        }
        rd.forward(request, response);
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
