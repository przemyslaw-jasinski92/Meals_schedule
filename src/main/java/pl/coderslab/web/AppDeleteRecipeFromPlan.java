package pl.coderslab.web;

import pl.coderslab.dao.RecipePlanDao;
import pl.coderslab.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "AppDeleteRecipeFromPlan", value = "/app/plan/recipe/delete")
public class AppDeleteRecipeFromPlan extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String recipePlanIdValue = request.getParameter("id");
        if (recipePlanIdValue == null || recipePlanIdValue.isBlank()) {
            response.sendRedirect("/app/plan/list");
            return;
        }
        try {
            int recipePlanId = Integer.parseInt(recipePlanIdValue);
            RecipePlanDao recipePlanDao = new RecipePlanDao();
            recipePlanDao.delete(recipePlanId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        Plan plan = (Plan) session.getAttribute("planDetailsInfo");
        response.sendRedirect("/app/plan/details?id=" + plan.getId() + "&msg=" + URLEncoder.encode("Usunięto przepis z planu", StandardCharsets.UTF_8));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
