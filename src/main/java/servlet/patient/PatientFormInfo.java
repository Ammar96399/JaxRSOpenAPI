package servlet.patient;

import fr.istic.taa.jaxrs.dao.PatientDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="PatientAdd",
        urlPatterns={"/PatientAdd"})
public class PatientFormInfo extends HttpServlet {

    /*public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        var lastname = request.getParameter("lastname");
        var firstname = request.getParameter("firstname");

        var patientDao = new PatientDAO();

        patientDao.createPatients(firstname, lastname);
    }*/
}
