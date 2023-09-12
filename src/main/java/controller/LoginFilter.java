package controller;

import Utils.CookieUtil;
import models.User;
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

    private static final String SESSION_ID = "session-id";
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
        Set<String> allowedUrls = Set.of("/registration", "/assets");
        if (allowedUrls.contains(req.getServletPath())) {
            chain.doFilter(request, response);
        }
        Optional<Cookie> optionalCookie = CookieUtil.findCookieByName(req, SESSION_ID);
        if (optionalCookie.isEmpty()) {
            String login = req.getParameter("login");
            String password = req.getParameter("password");

            try {
                User user = usersService.findUserByLoginPassword(login, password);
                if(user != null){
                    //TODO Change logic in findSessionUser for searching by userId instead of sessionId
                    res.addCookie(new Cookie(SESSION_ID, usersService));
                }
            } catch (AccountNotFoundException e) {
                templateEngine.render("login.ftl", res);
            }

        }
    }
}
