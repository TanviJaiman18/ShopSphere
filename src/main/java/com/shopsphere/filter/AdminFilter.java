package com.shopsphere.filter;
import jakarta.servlet.*;import jakarta.servlet.annotation.WebFilter;import jakarta.servlet.http.*;import java.io.IOException;
@WebFilter("/admin/*")
public class AdminFilter implements Filter {
 public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain)throws IOException,ServletException{
  HttpServletRequest req=(HttpServletRequest)request;HttpServletResponse res=(HttpServletResponse)response;HttpSession s=req.getSession(false);
  if(s==null||s.getAttribute("user")==null){res.sendRedirect(req.getContextPath()+"/login");return;}
  if(!"ADMIN".equals(s.getAttribute("role"))){res.sendError(403,"Admin access only");return;}chain.doFilter(request,response);
 }
}
