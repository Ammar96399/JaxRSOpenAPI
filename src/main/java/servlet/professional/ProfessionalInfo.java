package servlet.professional;

import fr.istic.taa.jaxrs.dao.ProfessionalDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

@WebServlet(name="Professional",
        urlPatterns={"/Professional/*"})
public class ProfessionalInfo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var id = Long.parseLong(req.getPathInfo().substring(1));
        if (Objects.nonNull(id)) {
            var dao = new ProfessionalDAO();
            var px = dao.getById(id);

            PrintWriter p = new PrintWriter(resp.getOutputStream());
            p.println("<html><body>\n" +
                    "<h1>Récapitulatif du professionnel</h1>\n" +
                    px.toString() + "\n" +
                    "</body></html>");
            p.flush();
        }
    }
}
