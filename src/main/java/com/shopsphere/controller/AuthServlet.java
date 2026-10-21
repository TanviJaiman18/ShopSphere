package com.shopsphere.controller;
import com.shopsphere.dao.UserDAO; import com.shopsphere.util.Passwords; import jakarta.servlet.annotation.WebServlet; import jakarta.servlet.http.*; import jakarta.servlet.*; import java.io.IOException;
@WebServlet(urlPatterns={"/login","/register","/logout"})
public class AuthServlet extends HttpServlet {
 protected void doGet(HttpServletRequest q,HttpServletResponse r)throws ServletException,IOException {String p=q.getServletPath();if(p.equals("/logout")){HttpSession s=q.getSession(false);if(s!=null)s.invalidate();r.sendRedirect(q.getContextPath()+"/");}else q.getRequestDispatcher(p.equals("/login")?"/login.jsp":"/register.jsp").forward(q,r);}
 protected void doPost(HttpServletRequest q,HttpServletResponse r)throws ServletException,IOException {
  String path=q.getServletPath();String email=q.getParameter("email")==null?"":q.getParameter("email").trim();String password=q.getParameter("password")==null?"":q.getParameter("password");
  try {
   UserDAO dao=new UserDAO();
   if(path.equals("/register")){String name=q.getParameter("name"),mobile=q.getParameter("mobile");if(name==null||name.isBlank()||!email.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")||password.length()<6){q.setAttribute("error","Enter a valid name/email and password of at least 6 characters.");q.getRequestDispatcher("/register.jsp").forward(q,r);return;}dao.register(name.trim(),email,Passwords.hash(password),mobile);r.sendRedirect(q.getContextPath()+"/login?registered=1");return;}
   var user=dao.findLogin(email,Passwords.hash(password));if(user==null){q.setAttribute("error","Invalid email or password.");q.getRequestDispatcher("/login.jsp").forward(q,r);return;}
   q.getSession(true).setAttribute("user",user);q.getSession().setAttribute("role",user.getRole());r.sendRedirect(q.getContextPath()+(user.getRole().equals("ADMIN")?"/admin/products":"/"));
  }catch(java.sql.SQLIntegrityConstraintViolationException e){q.setAttribute("error","That email is already registered.");q.getRequestDispatcher("/register.jsp").forward(q,r);}catch(Exception e){throw new ServletException(e);}
 }
}
