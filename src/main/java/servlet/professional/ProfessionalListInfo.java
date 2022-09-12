package servlet.professional;

import fr.istic.taa.jaxrs.dao.ProfessionalDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="Professionals",
        urlPatterns={"/Professionals"})
public class ProfessionalListInfo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var dao = new ProfessionalDAO();
        var px = dao.listProfessionals();

        PrintWriter p = new PrintWriter(resp.getOutputStream());
        p.println("<html><body>\n" +
                "<h1>Récapitulatif du professionnel</h1>\n");
        p.println("<ul>");
        px.forEach(prof -> p.println("<li>" + prof.getFirstName() + " " + prof.getLastName() + "</li>"));
        p.println("</ul>");
        p.println("</body></html>");
        p.flush();
    }
}