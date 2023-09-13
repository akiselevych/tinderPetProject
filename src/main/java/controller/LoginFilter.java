package controller;

import Utils.CookieUtil;
import models.SessionUser;
import services.UsersService;

import javax.security.auth.login.AccountNotFoundException;
import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;

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
        Set<String> allowedUrls = Set.of("/registration", "/css/bootstrap.min.css","/js/bootstrap.min.css" , "/dashboard");
        if (allowedUrls.contains(req.getServletPath())) {
            chain.doFilter(request, response);
        }
        Optional<Cookie> optionalCookie = CookieUtil.findCookieByName(req, SESSION_ID);
        if (optionalCookie.isEmpty()){
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            if (login != null && password != null) {
                try {
                    SessionUser user = usersService.findUserByLoginPassword(login, password);
                    if (user != null) {
                        req.getSession(true);
                        res.addCookie(new Cookie(SESSION_ID, String.valueOf(user.getSessionId())));
                        req.getSession().setAttribute(SESSION_USER_ID, user.getId());
                        req.getSession().setAttribute(SHOWING_USER_INDEX, 0);
                        res.sendRedirect("/dashboard");
                        chain.doFilter(request, response);
                        return;
                    }
                    templateEngine.render("login.ftl",res);
                } catch (AccountNotFoundException e) {
                    templateEngine.render("login.ftl",res);
                    e.printStackTrace();
                }
            }
            templateEngine.render("login.ftl",res);
        } else {
            chain.doFilter(req, res);
        }

    }
}
