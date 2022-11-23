package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AppPlanEdit", value = "/app/plan/edit")
public class AppPlanEdit extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String planIdValue = request.getParameter("id");
        if (planIdValue == null || planIdValue.isBlank()) {
            response.sendRedirect("/app/plan/list");
            return;
        }
        try {
            int planId = Integer.parseInt(planIdValue);
            PlanDao planDao = new PlanDao();
            Plan plan = planDao.read(planId);
            session.setAttribute("editPlan", plan);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher(request.getContextPath() + "/appPlanEdit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;character=utf-8");
        String planIdValue = request.getParameter("planId");
        String planName = request.getParameter("name");
        String planDescription = request.getParameter("plan_description");

        try {
            int planId = Integer.parseInt(planIdValue);
            Plan plan = new Plan();
            plan.setName(planName);
            plan.setDescription(planDescription);
            plan.setId(planId);
            PlanDao planDao = new PlanDao();
            planDao.update(plan);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/app/plan/list");
    }
}
