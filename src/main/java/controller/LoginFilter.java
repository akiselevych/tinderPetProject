package controller;

import models.SessionUser;
import services.UsersService;

import javax.security.auth.login.AccountNotFoundException;
import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

public class LoginFilter implements Filter {

    public static final String SESSION_ID = "session-id";
    public static final String SESSION_USER_ID = "session-user-id";
    public static final String SHOWING_USER_INDEX = "showing-user-index";
    private TemplateEngine templateEngine;
    private UsersService usersService;

    public LoginFilter(TemplateEngine templateEngine, UsersService usersService) {
        this.templateEngine = templateEngine;
        this.usersService = usersService;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        boolean isAsset = Objects.equals(req.getServletPath(), "/registration") || Objects.equals(req.getServletPath(), "/css/bootstrap.min.css") || Objects.equals(req.getServletPath(), "/js/bootstrap.min.css");
        if (isAsset){
            chain.doFilter(req, res);
            return;
        }
        if (session != null) {
            chain.doFilter(req, res);
        } else {
            try {
                SessionUser user = usersService.findUserByLoginPassword(request.getParameter("login"), request.getParameter("password"));
                session = req.getSession(true);
                session.setMaxInactiveInterval(60_000);
                session.setAttribute(SESSION_USER_ID, user.getId());
                session.setAttribute(SHOWING_USER_INDEX, 0);
                Cookie cookie = new Cookie(SESSION_ID, String.valueOf(user.getSessionId()));
                res.addCookie(cookie);
                chain.doFilter(req, res);
                return;
            } catch (AccountNotFoundException e) {
                templateEngine.render("login.ftl",res);
            }
        }
    }
}
