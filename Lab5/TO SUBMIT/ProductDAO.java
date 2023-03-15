package dao;

import database.MySQLConnUtils;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class ProductDAO {
    public static ProductDAO getInstance(){
        return new ProductDAO();
    }
    public boolean addProduct(Product product){
        Boolean flag = false;
        try{
            Connection conn = MySQLConnUtils.getInstance().getConnection();
            PreparedStatement statement = conn.prepareStatement("Insert into product (name,price) values(?,?)");
            statement.setString(1,product.getName());
            statement.setDouble(2,product.getPrice());
            if(statement.executeUpdate() > 0){
                flag = true;
            }
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            return flag;
        }
    }

    public boolean deleteProduct(Integer id){
        Boolean flag = false;
        try{
            Connection conn = MySQLConnUtils.getInstance().getConnection();
            PreparedStatement statement = conn.prepareStatement("Delete from product where id = ?");
            statement.setInt(1,id);
            if(statement.executeUpdate() > 0){
                flag = true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            return flag;
        }
    }
}
