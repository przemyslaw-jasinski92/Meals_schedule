package pl.coderslab.web;

import pl.coderslab.dao.DayNameDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.dao.RecipePlanDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.DayName;
import pl.coderslab.model.Plan;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddRecipeToPlan", value = "/app/recipe/plan/add")
public class AddRecipeToPlan extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");

        PlanDao planDao = new PlanDao();
        List<Plan> plansList = planDao.findAllAdminPlans(admin);

        RecipeDao recipeDao = new RecipeDao();
        List<Recipe> allRecipes = recipeDao.findAdminAll(admin);

        DayNameDao dayNameDao = new DayNameDao();
        List<DayName> daysName = dayNameDao.findAll();

        session.setAttribute("days", daysName);
        session.setAttribute("recipes", allRecipes);
        session.setAttribute("plans", plansList);
        request.getRequestDispatcher(request.getContextPath() + "/AddRecipeToPlan.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;character=utf-8");
        String planName = request.getParameter("plan_name");
        String mealName = request.getParameter("meal_name");
        int mealNumber = Integer.parseInt(request.getParameter("meal_number"));
        String recipeName = request.getParameter("recipes");
        String day = request.getParameter("days");

        PlanDao planDao = new PlanDao();
        int planId = planDao.getPlanId(planName);
        DayNameDao dayNameDao = new DayNameDao();
        int dayId = dayNameDao.findDayId(day);
        RecipeDao recipeDao = new RecipeDao();
        int recipeId = recipeDao.findRecipeId(recipeName);

        RecipePlanDao recipePlanDao = new RecipePlanDao();
        recipePlanDao.addRecipeToPlan(recipeId, mealName, mealNumber, dayId, planId);
        response.sendRedirect("/app/recipe/plan/add");

    }
}
