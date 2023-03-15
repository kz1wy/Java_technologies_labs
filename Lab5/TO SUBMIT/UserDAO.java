package dao;
import model.User;
import database.MySQLConnUtils;

import java.sql.*;

public class UserDAO {
    public static UserDAO getInstance(){
        return new UserDAO();
    }
    public boolean login(String email, String password){
        Boolean flag = false;
        try{
            Connection conn = MySQLConnUtils.getInstance().getConnection();
            PreparedStatement statement = conn.prepareStatement("Select * from User where email = ? and password = ?");
            statement.setString(1,email);
            statement.setString(2,password);
            if(statement.execute()){
                flag = true;
            }
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            return flag;
        }
    }

    public boolean register(User user) {
        boolean flag = false;
        try {
            if (isEmailExist(user.getEmail())) {
                return false;
            }
            Connection conn = MySQLConnUtils.getInstance().getConnection();
            PreparedStatement statement = conn.prepareStatement("INSERT INTO user (name, email, password) VALUES (?, ?, ?)");
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            if (statement.executeUpdate() > 0) {
                flag = true;
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean isEmailExist(String email){
        Boolean flag = false;
        try{
            Connection conn = MySQLConnUtils.getInstance().getConnection();
            PreparedStatement statement = conn.prepareStatement("Select * from user where email = ?");
            statement.setString(1,email);
            if(statement.execute()){
                flag = true;
            }
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            return flag;
        }
    }

    public String getUsername(String email){
        String name = null;
        try{
            Connection conn = MySQLConnUtils.getInstance().getConnection();
            PreparedStatement statement = conn.prepareStatement("Select * from User where email = ?");
            statement.setString(1,email);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                name = rs.getString("name");
            }
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            return name;
        }
    }
}
