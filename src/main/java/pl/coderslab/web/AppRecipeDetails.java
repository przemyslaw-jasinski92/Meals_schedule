package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AppRecipeDetails", value = "/app/recipe/details")
public class AppRecipeDetails extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String idValue = request.getParameter("id");

        if (idValue == null || idValue.isBlank()) {
            response.sendRedirect("/app/recipe/list");
        }

        try {
            int id = Integer.parseInt(idValue);
            RecipeDao recipeDao = new RecipeDao();
            Recipe recipe = recipeDao.read(id);
            session.setAttribute("recipeDetails", recipe);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher(request.getContextPath() + "/addRecipeDetails.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
