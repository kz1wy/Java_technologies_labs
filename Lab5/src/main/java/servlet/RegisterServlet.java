package servlet;

import dao.UserDAO;
import model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("logged") == null){
            if(req.getSession().getAttribute("error") != null){
                req.setAttribute("error",req.getSession().getAttribute("error"));
                req.getSession().removeAttribute("error");
            }
            req.getRequestDispatcher("jsp/register.jsp").forward(req,resp);
        }else{
            resp.sendRedirect("product");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        if(req.getSession().getAttribute("logged") != null){
            resp.sendRedirect("/");
            return;
        }
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String passwordConfirm = req.getParameter("password-confirm");
        User user = new User(name, email, password);
        if(name == "" || email == "" || password == "" || passwordConfirm == ""){
            req.getSession().setAttribute("error","Please fill all the blanks");
            resp.sendRedirect("/register");
        }else{
            if(!passwordConfirm.equals(password)){
                req.getSession().setAttribute("error","Confirm password does not match password");
                resp.sendRedirect("/register");
            }
            if(UserDAO.getInstance().register(user)){
                resp.sendRedirect("/login");
            }else{
                req.getSession().setAttribute("error","Invalid information");
                resp.sendRedirect("/register");
            }
        }

    }
}
