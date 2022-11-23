package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "AppDeleteRecipe", value = "/app/recipe/delete")
public class AppDeleteRecipe extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String recipeIdValue = request.getParameter("id");

        if (recipeIdValue == null || recipeIdValue.isBlank()) {
            response.sendRedirect("/app/recipe/list");
            return;
        }

        try {
            int recipeId = Integer.parseInt(recipeIdValue);
            RecipeDao recipeDao = new RecipeDao();
            recipeDao.delete(recipeId);

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/app/recipe/list?msg=" + URLEncoder.encode("Usunięto przepis", StandardCharsets.UTF_8));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
