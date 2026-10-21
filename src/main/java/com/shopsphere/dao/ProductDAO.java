package com.shopsphere.dao;
import com.shopsphere.model.Product; import com.shopsphere.util.DB;
import java.sql.*; import java.util.*;
public class ProductDAO {
 private Product map(ResultSet r)throws SQLException { Product p=new Product();p.setProductId(r.getInt("product_id"));p.setCategoryId(r.getInt("category_id"));p.setName(r.getString("name"));p.setBrand(r.getString("brand"));p.setDescription(r.getString("description"));p.setPrice(r.getDouble("price"));p.setDiscount(r.getDouble("discount"));p.setStock(r.getInt("stock"));p.setImageUrl(r.getString("image_url"));return p; }
 public List<Product> all(String q)throws SQLException {List<Product> out=new ArrayList<>();String sql="SELECT * FROM products WHERE status=1 AND (?='' OR name LIKE ? OR brand LIKE ? OR description LIKE ?) ORDER BY created_at DESC";
 try(Connection c=DB.getConnection();PreparedStatement p=c.prepareStatement(sql)){String s=q==null?"":q.trim();p.setString(1,s);p.setString(2,"%"+s+"%");p.setString(3,"%"+s+"%");p.setString(4,"%"+s+"%");try(ResultSet r=p.executeQuery()){while(r.next())out.add(map(r));}}return out;}
 public Product find(int id)throws SQLException {try(Connection c=DB.getConnection();PreparedStatement p=c.prepareStatement("SELECT * FROM products WHERE product_id=? AND status=1")){p.setInt(1,id);try(ResultSet r=p.executeQuery()){return r.next()?map(r):null;}}}
 public void save(Product x)throws SQLException {String sql="INSERT INTO products(category_id,name,brand,description,price,discount,stock,image_url,status) VALUES(?,?,?,?,?,?,?,?,1)";
 try(Connection c=DB.getConnection();PreparedStatement p=c.prepareStatement(sql)){if(x.getCategoryId()>0)p.setInt(1,x.getCategoryId());else p.setNull(1,Types.INTEGER);p.setString(2,x.getName());p.setString(3,x.getBrand());p.setString(4,x.getDescription());p.setDouble(5,x.getPrice());p.setDouble(6,x.getDiscount());p.setInt(7,x.getStock());p.setString(8,x.getImageUrl());p.executeUpdate();}}
 public void delete(int id)throws SQLException {try(Connection c=DB.getConnection();PreparedStatement p=c.prepareStatement("UPDATE products SET status=0 WHERE product_id=?")){p.setInt(1,id);p.executeUpdate();}}
}
