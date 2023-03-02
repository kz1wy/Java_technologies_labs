package servlet;

import java.io.*;
import java.util.HashMap;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "loginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    private HashMap<String, String> accounts;

    @Override
    public void init() throws ServletException {
        super.init();

        // Initialize the list of pre-built accounts
        accounts = new HashMap<>();
        accounts.put("user", "123");
        accounts.put("admin", "123");
        accounts.put("client", "123");
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("index.jsp");
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Check if the account exists in the HashMap
        if (accounts.containsKey(username) && accounts.get(username).equals(password)) {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body><h4>Name/Password match</h4></body></html>");
        } else {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body><h4>Name/Password does not match</h4></body></html>");
        }
    }
}