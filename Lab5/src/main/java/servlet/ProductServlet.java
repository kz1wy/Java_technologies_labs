package servlet;

import com.google.gson.Gson;
import dao.ProductDAO;
import model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("logged") == null){
            resp.sendRedirect("login");
            return;
        }
        if(req.getSession().getAttribute("error") != null){
            req.setAttribute("error",req.getSession().getAttribute("error"));
            req.getSession().removeAttribute("error");
        }
        if(req.getSession().getAttribute("message") != null){
            req.setAttribute("message",req.getSession().getAttribute("message"));
            req.getSession().removeAttribute("message");
        }
        if(req.getSession().getAttribute("username") != null){
            req.setAttribute("username",req.getSession().getAttribute("username"));
        }
        req.getRequestDispatcher("jsp/index.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("name") == "" || req.getParameter("price") == "") {
            req.getSession().setAttribute("error","Please file name/price of product");
            resp.sendRedirect("product");
            return;
        }
        String name = req.getParameter("name");
        Double price = Double.parseDouble(req.getParameter("price"));
        Product product = new Product(name,price);
        if(ProductDAO.getInstance().addProduct(product)){
            req.getSession().setAttribute("message","Add successfully");
            resp.sendRedirect("product");
        }else{
            req.getSession().setAttribute("error","Can not add product");
            resp.sendRedirect("product");
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        HashMap<String, Object> data = new HashMap<>();
        if(req.getParameter("id") == null){
            data.put("status","fail");
            data.put("message","Id can not be null");
            resp.getWriter().println(gson.toJson(data));
            return;
        }
        if(ProductDAO.getInstance().deleteProduct(Integer.parseInt(req.getParameter("id")))){
            data.put("status","success");
            data.put("message","Delete succesfully");
            resp.getWriter().println(gson.toJson(data));
            return;
        }
        data.put("status","fail");
        data.put("message","Can not delete product");
        resp.getWriter().println(gson.toJson(data));
    }
}
