package controller;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class IndexServlet extends HttpServlet {
    private final TemplateEngine templateEngine;

    public IndexServlet(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);

        if (session.getAttribute("session-id") == null && session.getAttribute("showing-user-index") == null) {
            session.setAttribute("session-id", 4);
            session.setAttribute("session-user-id", 4);
            session.setAttribute("showing-user-index", 0);
        }
        templateEngine.render("/dashboard.ftl", response);
    }
}
