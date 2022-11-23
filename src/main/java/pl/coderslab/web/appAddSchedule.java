package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "appAddSchedule", value = "/app/plan/add")
public class appAddSchedule extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(request.getContextPath() + "/addSchedule.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;character=utf-8");
        HttpSession session = request.getSession();
        String planName = request.getParameter("name");
        String planDescription = request.getParameter("plan_description");

        Admin admin = new Admin();
        admin = (Admin) session.getAttribute("admin");
        PlanDao planDao = new PlanDao();
        Plan plan = new Plan();
        plan.setName(planName);
        plan.setDescription(planDescription);
        plan.setAdminId(admin.getId());
        planDao.create(plan);
        response.sendRedirect("/app/plan/list");

    }
}
