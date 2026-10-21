package com.shopsphere.dao;
import com.shopsphere.model.User; import com.shopsphere.util.DB; import java.sql.*;
public class UserDAO {
 public boolean register(String name,String email,String hash,String mobile)throws SQLException {
  try(Connection c=DB.getConnection();PreparedStatement p=c.prepareStatement("INSERT INTO users(name,email,password_hash,mobile) VALUES(?,?,?,?)")){p.setString(1,name);p.setString(2,email.toLowerCase());p.setString(3,hash);p.setString(4,mobile);return p.executeUpdate()==1;}
 }
public User login(String email) throws SQLException {
    return null;
}
 public User findLogin(String email,String hash)throws SQLException {
  try(Connection c=DB.getConnection();PreparedStatement p=c.prepareStatement("SELECT user_id,name,email,mobile,role FROM users WHERE email=? AND password_hash=? AND status=1")){p.setString(1,email.toLowerCase());p.setString(2,hash);try(ResultSet r=p.executeQuery()){if(!r.next())return null;return new User(r.getInt("user_id"),r.getString("name"),r.getString("email"),r.getString("mobile"),r.getString("role"));}}
 }
}

