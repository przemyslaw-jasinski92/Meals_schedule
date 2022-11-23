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

@WebServlet(name = "RecipeDetails", value = "/recipe/details")
public class RecipeDetails extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String recipeIdValue = request.getParameter("id");

        if(recipeIdValue == null || recipeIdValue.isBlank()){
            response.sendRedirect("/recipe/list");
            return;
        }

        try {
            int recipeId = Integer.parseInt(recipeIdValue);
            RecipeDao recipeDao = new RecipeDao();
            Recipe recipe = recipeDao.read(recipeId);
            session.setAttribute("mainRecipeDetails", recipe);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher(request.getContextPath() + "/recipe-details.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
