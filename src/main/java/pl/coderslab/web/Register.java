package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Register", value = "/register")
public class Register extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        //nie sprawdzam czy pola są puste, ponieważ w formularzu jest 'required' na te pola. Nigdy nie będą puste.

        Admin newAdmin = new Admin();
        newAdmin.setFirst_name(name);
        newAdmin.setLast_name(surname);
        newAdmin.setEmail(email);
        newAdmin.setPassword(password);

        AdminDao adminDao = new AdminDao();
        adminDao.create(newAdmin);
        response.sendRedirect("/login");
    }
}
