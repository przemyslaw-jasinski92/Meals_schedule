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

@WebServlet(name = "Login", value = "/login")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("message", "");
        String msg = request.getParameter("msg");
        if (msg != null) {
            session.setAttribute("message", msg);
        }
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Admin admin = new Admin();
        admin.setEmail(email);
        admin.setPassword(password);
        AdminDao adminDao = new AdminDao();
        if (adminDao.isAdminExists(admin) != null) {
            Admin loggedAdmin = adminDao.isAdminExists(admin);
            session.setAttribute("admin", loggedAdmin);
            response.sendRedirect("/app");
            return;
        }
        response.sendRedirect("/login");
    }
}
