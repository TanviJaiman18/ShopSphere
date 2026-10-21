package com.shopsphere.controller;
import com.shopsphere.model.*;import com.shopsphere.util.DB;import jakarta.servlet.annotation.WebServlet;import jakarta.servlet.http.*;import jakarta.servlet.*;import java.io.IOException;import java.sql.*;import java.util.*;
@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
 @SuppressWarnings("unchecked") protected void doGet(HttpServletRequest q,HttpServletResponse r)throws ServletException,IOException{Map<Integer,CartItem> m=(Map<Integer,CartItem>)q.getSession().getAttribute("cart");if(m==null||m.isEmpty()){r.sendRedirect(q.getContextPath()+"/cart");return;}q.setAttribute("cartItems",m.values());q.getRequestDispatcher("/checkout.jsp").forward(q,r);}
 @SuppressWarnings("unchecked") protected void doPost(HttpServletRequest q,HttpServletResponse r)throws ServletException,IOException{
  HttpSession s=q.getSession();User u=(User)s.getAttribute("user");Map<Integer,CartItem> cart=(Map<Integer,CartItem>)s.getAttribute("cart");if(cart==null||cart.isEmpty()){r.sendRedirect(q.getContextPath()+"/cart");return;}
  String line=q.getParameter("address"),city=q.getParameter("city"),state=q.getParameter("state"),pin=q.getParameter("pincode");
  if(line==null||line.isBlank()||city==null||city.isBlank()||state==null||state.isBlank()||pin==null||pin.isBlank()){q.setAttribute("error","Please complete your delivery address.");q.setAttribute("cartItems",cart.values());q.getRequestDispatcher("/checkout.jsp").forward(q,r);return;}
  try(Connection c=DB.getConnection()){c.setAutoCommit(false);try{
   int addressId;try(PreparedStatement p=c.prepareStatement("INSERT INTO addresses(user_id,address_line,city,state,pincode) VALUES(?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS)){p.setInt(1,u.getUserId());p.setString(2,line);p.setString(3,city);p.setString(4,state);p.setString(5,pin);p.executeUpdate();try(ResultSet k=p.getGeneratedKeys()){k.next();addressId=k.getInt(1);}}
   double total=0;for(CartItem i:cart.values())total+=i.getSubtotal();int orderId;
   try(PreparedStatement p=c.prepareStatement("INSERT INTO orders(user_id,address_id,total_amount,payment_method,payment_status,order_status) VALUES(?,?,?,'COD','PENDING','PLACED')",Statement.RETURN_GENERATED_KEYS)){p.setInt(1,u.getUserId());p.setInt(2,addressId);p.setDouble(3,total);p.executeUpdate();try(ResultSet k=p.getGeneratedKeys()){k.next();orderId=k.getInt(1);}}
   for(CartItem i:cart.values()){try(PreparedStatement p=c.prepareStatement("UPDATE products SET stock=stock-? WHERE product_id=? AND stock>=? AND status=1")){p.setInt(1,i.getQuantity());p.setInt(2,i.getProduct().getProductId());p.setInt(3,i.getQuantity());if(p.executeUpdate()!=1)throw new SQLException("Not enough stock for "+i.getProduct().getName());}
    try(PreparedStatement p=c.prepareStatement("INSERT INTO order_items(order_id,product_id,quantity,price) VALUES(?,?,?,?)")){p.setInt(1,orderId);p.setInt(2,i.getProduct().getProductId());p.setInt(3,i.getQuantity());p.setDouble(4,i.getProduct().getFinalPrice());p.executeUpdate();}}
   c.commit();cart.clear();s.setAttribute("lastOrderId",orderId);r.sendRedirect(q.getContextPath()+"/order-success.jsp");
  }catch(Exception e){c.rollback();throw e;}finally{c.setAutoCommit(true);}
  }catch(Exception e){throw new ServletException("Checkout failed. Verify product stock and database setup.",e);}
 }
}
