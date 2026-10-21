package com.shopsphere.controller;
import com.shopsphere.dao.ProductDAO; import jakarta.servlet.annotation.WebServlet; import jakarta.servlet.http.*; import jakarta.servlet.*; import java.io.IOException;
@WebServlet("/product")
public class ProductDetailsServlet extends HttpServlet {
 protected void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException {try{int id=Integer.parseInt(req.getParameter("id"));var p=new ProductDAO().find(id);if(p==null){res.sendError(404);return;}req.setAttribute("product",p);req.getRequestDispatcher("/product-details.jsp").forward(req,res);}catch(NumberFormatException e){res.sendError(400);}catch(Exception e){throw new ServletException(e);}}
}
