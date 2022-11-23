package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.dao.RecipePlanDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.RecipePlan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "HomePageLogged", value = "/app")
public class HomePageLogged extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");

        RecipeDao recipeDao = new RecipeDao();
        int numberOfRecipes = recipeDao.countAdminRecipes(admin);
        PlanDao planDao = new PlanDao();
        int numberOfPlans = planDao.countAdminPlans(admin);

        RecipePlanDao recipePlanDao = new RecipePlanDao();
        String lastNamePlan = recipePlanDao.getLastPlanName(admin);
        Map<String, List<RecipePlan>> lastPlan = recipePlanDao.getLastPlan(admin);

        session.setAttribute("lastPlan", lastPlan);
        session.setAttribute("lastNamePlan", lastNamePlan);
        session.setAttribute("plans", numberOfPlans);
        session.setAttribute("recipes", numberOfRecipes);
        request.getRequestDispatcher(request.getContextPath() + "/indexLogged.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String recipeIdValue = request.getParameter("recipeId");
        try {
            int recipeId = Integer.parseInt(recipeIdValue);
            response.sendRedirect("/app/recipe/details?id=" + recipeId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
