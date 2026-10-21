package com.shopsphere.controller;
import com.shopsphere.dao.ProductDAO; import jakarta.servlet.annotation.WebServlet; import jakarta.servlet.http.*;
import jakarta.servlet.*; import java.io.IOException;
@WebServlet({"/","/products"})
public class HomeServlet extends HttpServlet {
 protected void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException {
  try {req.setAttribute("products",new ProductDAO().all(req.getParameter("q")));req.setAttribute("q",req.getParameter("q")==null?"":req.getParameter("q"));req.getRequestDispatcher("/products.jsp").forward(req,res);}
  catch(Exception e){throw new ServletException("Unable to load products. Check MySQL and import database/shopsphere.sql.",e);}
 }
}
