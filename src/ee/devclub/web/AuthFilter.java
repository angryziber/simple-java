package ee.devclub.web;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static javax.servlet.http.HttpServletResponse.SC_FORBIDDEN;

public class AuthFilter implements Filter {
  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    HttpServletResponse response = (HttpServletResponse) servletResponse;

    HttpSession session = request.getSession(false);
    if (session == null) {
      if (request.getMethod().equals("POST") && request.getParameter("user") != null) {
        processLogin(request, response);
      }
      else {
        showLoginForm(request, response);
        return;
      }
    }

    filterChain.doFilter(servletRequest, servletResponse);
  }

  private void showLoginForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setStatus(SC_FORBIDDEN);
    request.getRequestDispatcher("/login.html").include(request, response);
  }

  private void processLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String user = request.getParameter("user");
    String password = request.getParameter("password");

    if (user.equals("bob") && password.equals("secret")) {
      HttpSession session = request.getSession(true);
      session.setAttribute("user", user);
    }
    else {
      response.sendError(SC_FORBIDDEN);
    }
  }

  @Override
  public void destroy() {
  }
}
