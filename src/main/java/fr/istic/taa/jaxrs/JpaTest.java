package fr.istic.taa.jaxrs;

import fr.istic.taa.jaxrs.dao.ChildDAO;
import fr.istic.taa.jaxrs.dao.ProfessionalDAO;
import fr.istic.taa.jaxrs.dao.generic.EntityManagerHelper;
import fr.istic.taa.jaxrs.domain.Child;
import fr.istic.taa.jaxrs.domain.Professional;

public class JpaTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		var manager = EntityManagerHelper.getEntityManager();
		var tx = manager.getTransaction();
		var patientDAO = new ProfessionalDAO();
		var childDAO = new ChildDAO();
		tx.begin();
		try {
			patientDAO.createProfessional(new Professional("Cambria", "Alpha"));
			patientDAO.createProfessional(new Professional("Maria", "Beta"));
			patientDAO.createProfessional(new Professional("Jean", "Citron"));
			childDAO.createChild(new Child("firstChild", "good", "parent", 12));

			patientDAO.getAll().forEach(System.out::println);

			System.out.println(patientDAO.getProfessionalByName("Maria", "Beta"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();
		manager.close();
		System.out.println("done ...");
	}

}
