package controller;


import io.github.cdimascio.dotenv.Dotenv;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.Map;
import java.util.Objects;

public class IndexServlet extends HttpServlet {
    private final TemplateEngine templateEngine;

    public IndexServlet(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
//        Map<String, Object> params = Map.of(
//                "user", request.getSession().getAttribute(LoginFilter.ACTIVE_USER_ATTR_NAME)
//        );
//        templateEngine.render("dashboard.ftl", params, response);
//    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        Dotenv dotenv = Dotenv.load();
        String dbLogin = dotenv.get("DB_LOGIN");

        String dbConnectionString = dotenv.get("DB_CONNECTION_URL");
        String dbPassword = dotenv.get("DB_PASSWORD");
        try (Connection connection = DriverManager.getConnection(dbConnectionString, dbLogin, dbPassword)) {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM \"test-table\"");

            while (resultSet.next()) {

                String name = resultSet.getString("name");
                System.out.println("test name output : " + name);
            }

        } catch (SQLException e){
            System.out.println("Something went wrong, with db interaction returning countries");
            e.printStackTrace();
        }
        templateEngine.render("dashboard.ftl", response);
    }
}
