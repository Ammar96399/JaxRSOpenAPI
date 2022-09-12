package servlet.patient;

import fr.istic.taa.jaxrs.dao.PatientDAO;
import fr.istic.taa.jaxrs.domain.Patient;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="patients",
        urlPatterns={"/Patients"})
public class PatientListInfo extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var patientDAO = new PatientDAO();
        var patientList = patientDAO.getPatientList();

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>\n<body>\n" +
                "<h1>Liste des patients</h1>\n" +
                "<ul>\n");
        for(Patient p: patientList) {
            out.println(
                    "<li>Nom: " +
                    p.getLastName() + "\n" +
                    "<li>Prenom: " +
                    p.getFirstName() + "\n" +
                    "<li>id: " +
                    p.getId() + "\n"
            );
        }
        out.println("</ul>\n" + "</body></html>");
    }
}
