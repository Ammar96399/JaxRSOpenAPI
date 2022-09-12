package servlet.patient;

import fr.istic.taa.jaxrs.dao.PatientDAO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="patientById", urlPatterns={"/Patient/*"})
public class PatientInfoById extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        var id = Long.parseLong(request.getPathInfo().substring(1));
        var patientDAO = new PatientDAO();
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>\n<body>\n" +
                "<h1>Le patient avec l'id: " + id + "</h1>" +
                "<h3>Nom: </h3>" + patientDAO.getById(id).getLastName() +
                "<h3>Prenom: </h3>" + patientDAO.getById(id).getFirstName() +
                "</body></html>");
    }
}
