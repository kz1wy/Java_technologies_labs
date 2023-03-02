package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class HomeServlet extends HttpServlet {

    public HomeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		List<String> pages = new ArrayList(Arrays.asList("contact", "help", "about"));
		RequestDispatcher rd = null;

		if (page != null) {
			if (pages.contains(page)) {
				rd = request.getRequestDispatcher(page + ".jsp");
			}
		} else {
			rd = request.getRequestDispatcher("home.jsp");
		}
		 rd.forward(request, response);
	}
}
