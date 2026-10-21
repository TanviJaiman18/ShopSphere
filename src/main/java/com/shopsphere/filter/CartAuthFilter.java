package com.shopsphere.filter;
import jakarta.servlet.*;import jakarta.servlet.annotation.WebFilter;import jakarta.servlet.http.*;import java.io.IOException;
@WebFilter(urlPatterns={"/cart","/checkout","/orders"})
public class CartAuthFilter implements Filter {
 public void doFilter(ServletRequest a,ServletResponse b,FilterChain c)throws IOException,ServletException{HttpServletRequest q=(HttpServletRequest)a;HttpSession s=q.getSession(false);if(s==null||s.getAttribute("user")==null){((HttpServletResponse)b).sendRedirect(q.getContextPath()+"/login");return;}c.doFilter(a,b);}
}
