package controller;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexServlet extends HttpServlet {
    private final TemplateEngine templateEngine;

    public IndexServlet(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        templateEngine.render("dashboard.ftl", response);
    }
}
