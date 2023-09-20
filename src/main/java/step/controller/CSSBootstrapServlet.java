package step.controller;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

public class CSSBootstrapServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest rq, HttpServletResponse rs) throws IOException {
        try (ServletOutputStream os = rs.getOutputStream()) {
            InputStream stream = this.getClass().getClassLoader().getResourceAsStream("css/bootstrap.min.css");
            if (stream != null) {
                byte[] css = new byte[1024];
                int bytesRead;
                while ((bytesRead = stream.read(css)) != -1) {
                    os.write(css, 0, bytesRead);
                }
                stream.close();
            } else {
                rs.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
//            Path path = Paths.get(fileName);
//            Files.copy(path, os);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}