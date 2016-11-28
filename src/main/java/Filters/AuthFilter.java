package Filters;// Import required java libraries
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

// Implements Filter class
public class AuthFilter implements Filter  {
    public void  init(FilterConfig config)
            throws ServletException
    {

    }
    public void  doFilter(ServletRequest request,
                          ServletResponse response,
                          FilterChain chain)
            throws java.io.IOException, ServletException {
        HttpSession session = ((HttpServletRequest) request).getSession();
        if (session.getAttribute("email") != null){
            chain.doFilter(request,response);
        } else {
            ((HttpServletResponse) response).sendRedirect("index.jsp");
        }
    }
    public void destroy( ){
      /* Called before the Filter instance is removed
      from service by the web container*/
    }
}