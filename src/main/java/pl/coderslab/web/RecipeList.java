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
import java.util.List;

@WebServlet(name = "RecipeList", value = "/recipe/list")
public class RecipeList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("messageRecipe", "");
        String msg = request.getParameter("msg");
        if(msg != null){
            session.setAttribute("messageRecipe", msg);
        }
        RecipeDao recipeDao = new RecipeDao();
        List<Recipe> recipesList = recipeDao.findAll();
        session.setAttribute("recipes", recipesList);

        request.getRequestDispatcher(request.getContextPath() + "/recipesList.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
