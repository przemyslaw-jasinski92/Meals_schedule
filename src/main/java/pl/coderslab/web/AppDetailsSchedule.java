package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipePlanDao;
import pl.coderslab.model.Plan;
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

@WebServlet(name = "AppDetailsSchedule", value = "/app/plan/details")
public class AppDetailsSchedule extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("message", "");
        String planIdValue = request.getParameter("id");
        if (planIdValue == null || planIdValue.isBlank()) {
            response.sendRedirect("/app/plan/list");
        }
        String msg = request.getParameter("msg");
        if(msg != null){
            session.setAttribute("message", msg);
        }
        try {
            int planId = Integer.parseInt(planIdValue);
            PlanDao planDao = new PlanDao();
            Plan plan = planDao.read(planId);
            RecipePlanDao recipePlanDao = new RecipePlanDao();
            Map<String, List<RecipePlan>> planDetails = recipePlanDao.getAllPlans(planId);

            session.setAttribute("planDetails", planDetails);
            session.setAttribute("planDetailsInfo", plan);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }


        request.getRequestDispatcher(request.getContextPath() + "/appDetailsSchedule.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
