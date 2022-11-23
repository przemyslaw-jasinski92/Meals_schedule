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

@WebServlet(name = "AppEditRecipe", value = "/app/recipe/edit")
public class AppEditRecipe extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String recipeIdValue = request.getParameter("id");
        if (recipeIdValue == null || recipeIdValue.isBlank()) {
            response.sendRedirect("/app/recipe/list");
            return;
        }
        try {
            int recipeId = Integer.parseInt(recipeIdValue);
            RecipeDao recipeDao = new RecipeDao();
            Recipe recipe = recipeDao.read(recipeId);
            session.setAttribute("editRecipe", recipe);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher(request.getContextPath() + "/appEditRecipe.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;character=utf-8");
        String recipeIdValue = request.getParameter("id");
        String recipeName = request.getParameter("name");
        String recipeDescription = request.getParameter("description");
        String preparationTimeValue = request.getParameter("preparation_time");
        String recipePreparation = request.getParameter("preparation");
        String recipeIngredients = request.getParameter("ingredients");

        try {
            int recipeId = Integer.parseInt(recipeIdValue);
            int recipePreparationTime = Integer.parseInt(preparationTimeValue);
            Recipe recipe = new Recipe();
            recipe.setName(recipeName);
            recipe.setDescription(recipeDescription);
            recipe.setIngredients(recipeIngredients);
            recipe.setPreparationTime(recipePreparationTime);
            recipe.setPreparation(recipePreparation);
            recipe.setId(recipeId);
            RecipeDao recipeDao = new RecipeDao();
            recipeDao.update(recipe);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/app/recipe/list");
    }
}
