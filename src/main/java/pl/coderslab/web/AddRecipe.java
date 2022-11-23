package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AddRecipe", value = "/app/recipe/add")
public class AddRecipe extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(request.getContextPath() + "/recipe-add-form.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;character=utf-8");
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String timePreparationValue = request.getParameter("time_preparation");
        String preparation = request.getParameter("preparation");
        String ingredients = request.getParameter("ingredients");

        try{
            int timePreparation = Integer.parseInt(timePreparationValue);
            Recipe recipe = new Recipe(name, description, ingredients, timePreparation, preparation, admin.getId());
            RecipeDao recipeDao = new RecipeDao();
            recipeDao.create(recipe);
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        response.sendRedirect("/app/recipe/list");
    }
}
