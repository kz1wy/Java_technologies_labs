package servlet;

import com.google.gson.Gson;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.MediaType;
import module.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/products/*")
public class ProductServlet extends HttpServlet {

    private List<Product> productList;
    private Gson gson = new Gson();

    @Override
    public void init() throws ServletException {
        super.init();

        // Initialize sample data
        productList = new ArrayList<>();
        productList.add(new Product(1, "Iphone 18 pro max plus extra ultra ultimate", 599.4));
        productList.add(new Product(2, "Xiaomi 13 pro", 288.3));
        productList.add(new Product(3, "Product 3", 132.0));
        productList.add(new Product(4, "Professional tools", 1.578));
        productList.add(new Product(5, "human", 2321.6782));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            //all products are returned
            response.setContentType("application/json;charset=UTF-8");
            String json = gson.toJson(productList);
            response.getWriter().println(json);
        } else {
            try {
                int id = Integer.parseInt(pathInfo.substring(1));
                Optional<Product> product = getProductById(id);
                if (product.isPresent()) {
                    response.setContentType("application/json;charset=UTF-8");
                    String json = gson.toJson(product.get());
                    response.getWriter().println(json);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "không tìm thấy sản phẩm nào với mã số " + id);
                }
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int id = Integer.valueOf(request.getParameter("id"));
            String name = request.getParameter("name");
            double price = Double.valueOf(request.getParameter("price"));

            if (getProductById(id).isPresent()) {
                response.sendError(HttpServletResponse.SC_CONFLICT, "id already exists");
                return;
            }

            Product product = new Product(id, name, price);
            productList.add(product);

            response.setStatus(HttpServletResponse.SC_CREATED);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().println(gson.toJson(product));
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "invalid values: " + e.getMessage());
        }
    }

    @Override
    @Consumes(MediaType.APPLICATION_JSON)
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.valueOf(request.getParameter("id"));
            String name = request.getParameter("name");
            double price = Double.valueOf(request.getParameter("price"));

            Optional<Product> optionalProduct = getProductById(id);
            if (!optionalProduct.isPresent()) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Không tìm thấy sản phẩm với mã số: " + id);
                return;
            }

            Product product = optionalProduct.get();
            product.setName(name);
            product.setPrice(price);

            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().println(gson.toJson(product));
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid: " + e.getMessage());
        }
    }

    @Override
    @Consumes(MediaType.APPLICATION_JSON)
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.valueOf(request.getParameter("id"));
        Optional<Product> product = getProductById(id);

        if (product.isPresent()) {
            productList.remove(product.get());
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Không tìm thấy sản phẩm với mã số: " + id);
        }
    }

    private Optional<Product> getProductById(int id) {
        return productList.stream()
                .filter(product -> product.getId() == id)
                .findFirst();
    }
}