package servlet.professional;

import fr.istic.taa.jaxrs.dao.ProfessionalDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="ProfessionalAdd",
        urlPatterns={"/ProfessionalAdd"})
public class ProfessionalAdd extends HttpServlet {
    /*@Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        var lastname = request.getParameter("lastname");
        var firstname = request.getParameter("firstname");

        var dao = new ProfessionalDAO();

        dao.createProfessional(lastname, firstname);

        out.println("Ajout d'un professionnel avec succès.");
    }*/
}
