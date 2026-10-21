package com.shopsphere.controller;
import com.shopsphere.dao.ProductDAO;import com.shopsphere.model.*;import jakarta.servlet.annotation.WebServlet;import jakarta.servlet.http.*;import jakarta.servlet.*;import java.io.IOException;import java.util.*;
@WebServlet("/cart")
public class CartServlet extends HttpServlet {
 @SuppressWarnings("unchecked") private Map<Integer,CartItem> cart(HttpSession s){Map<Integer,CartItem> m=(Map<Integer,CartItem>)s.getAttribute("cart");if(m==null){m=new LinkedHashMap<>();s.setAttribute("cart",m);}return m;}
 protected void doGet(HttpServletRequest q,HttpServletResponse r)throws ServletException,IOException{q.setAttribute("cartItems",cart(q.getSession()).values());q.getRequestDispatcher("/cart.jsp").forward(q,r);}
 protected void doPost(HttpServletRequest q,HttpServletResponse r)throws ServletException,IOException{try{Map<Integer,CartItem> m=cart(q.getSession());String action=q.getParameter("action");int id=Integer.parseInt(q.getParameter("id"));if("remove".equals(action))m.remove(id);else if("update".equals(action)){int qty=Math.max(1,Math.min(99,Integer.parseInt(q.getParameter("quantity"))));CartItem item=m.get(id);if(item!=null)item.setQuantity(qty);}else{Product p=new ProductDAO().find(id);if(p!=null&&p.getStock()>0){CartItem old=m.get(id);if(old==null)m.put(id,new CartItem(p,1));else old.setQuantity(Math.min(p.getStock(),old.getQuantity()+1));}}r.sendRedirect(q.getContextPath()+"/cart");}catch(Exception e){throw new ServletException(e);}}
}
