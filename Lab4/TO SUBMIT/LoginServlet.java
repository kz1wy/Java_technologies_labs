package servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
    private Map<String, String> accounts = new HashMap<>();
    public LoginServlet() {
        // Add pre-built accounts to the HashMap
        accounts.put("admin", "123");
        accounts.put("user0", "123");
        accounts.put("user1", "321");
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("index.jsp");
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String get_pwd = accounts.get(username);

        //if username not in hashmap, get_pwd == null;
        if (get_pwd != null && get_pwd.equals(password)) {
            response.setContentType("text/html");
            response.getWriter().write("<h1>Name/Password match</h1>");
        } else {
            response.setContentType("text/html");
            response.getWriter().write("<h1>Name/Password does not match</h1>");
        }
    }
}
