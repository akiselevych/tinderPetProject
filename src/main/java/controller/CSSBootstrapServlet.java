package controller;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CSSBootstrapServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest rq, HttpServletResponse rs) throws IOException {
        try (ServletOutputStream os = rs.getOutputStream()) {
            URI fileName = this.getClass().getClassLoader().getResource("css/bootstrap.min.css").toURI();
            Path path = Paths.get(fileName);
            Files.copy(path, os);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}