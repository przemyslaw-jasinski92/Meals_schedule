package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "AppDeletePlan", value = "/app/plan/delete")
public class AppDeletePlan extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String planIdValue = request.getParameter("id");
        if (planIdValue == null || planIdValue.isBlank()) {
            response.sendRedirect("/app/plan/list");
            return;
        }
        try {
            int planId = Integer.parseInt(planIdValue);
            PlanDao planDao = new PlanDao();
            planDao.delete(planId);

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/app/plan/list?msg=" + URLEncoder.encode("Usunięto plan", StandardCharsets.UTF_8));
        //test
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
