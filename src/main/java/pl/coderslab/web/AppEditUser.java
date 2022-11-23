package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AppEditUser", value = "/app/user/edit")
public class AppEditUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(request.getContextPath() + "/appEditUser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;character=utf-8");
        HttpSession session = request.getSession();
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");

        Admin admin = (Admin) session.getAttribute("admin");
        admin.setFirst_name(name);
        admin.setLast_name(surname);
        admin.setEmail(email);
        AdminDao adminDao = new AdminDao();
        adminDao.update(admin);
        response.sendRedirect("/app");
    }
}
