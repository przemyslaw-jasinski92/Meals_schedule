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
import java.util.List;

@WebServlet(name = "ScheduleList", value = "/app/plan/list")
public class ScheduleList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("message", "");
        Admin admin = (Admin) session.getAttribute("admin");
        String msg = request.getParameter("msg");
        if(msg != null){
            session.setAttribute("message", msg);
        }
        PlanDao planDao = new PlanDao();
        List<Plan> plansList = planDao.findAllAdminPlans(admin);
        session.setAttribute("plans", plansList);

        request.getRequestDispatcher("/listSchedule.jsp").forward(request, response);
    }
}