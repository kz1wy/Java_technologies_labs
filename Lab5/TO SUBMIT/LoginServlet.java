package servlet;

import dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final int COOKIE = 2592000; //second
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("logged") == null) {
            if (req.getSession().getAttribute("error") != null) {
                req.setAttribute("error", req.getSession().getAttribute("error"));
                req.getSession().removeAttribute("error");
            }
            req.getRequestDispatcher("jsp/login.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("product");
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        if (email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            req.getSession().setAttribute("error", "Email/Password cannot be empty");
            resp.sendRedirect("/login");
        } else {
            if (UserDAO.getInstance().login(email, password)) {
                HttpSession session = req.getSession();
                session.setAttribute("logged", true);
                session.setAttribute("username", UserDAO.getInstance().getUsername(email));
                if (req.getParameter("remember") != null) {
                    Cookie emailCookie = new Cookie("email", email);
                    emailCookie.setMaxAge(COOKIE);
                    resp.addCookie(emailCookie);
                    Cookie passwordCookie = new Cookie("password", password);
                    passwordCookie.setMaxAge(COOKIE);
                    resp.addCookie(passwordCookie);
                } else {
                    Cookie emailCookie = new Cookie("email", "");
                    emailCookie.setMaxAge(0);
                    resp.addCookie(emailCookie);
                    Cookie passwordCookie = new Cookie("password", "");
                    passwordCookie.setMaxAge(0);
                    resp.addCookie(passwordCookie);
                }
                resp.sendRedirect("/product");
            } else {
                req.getSession().setAttribute("error", "Invalid Email/Password");
                resp.sendRedirect("/login");
            }
        }
    }
}
