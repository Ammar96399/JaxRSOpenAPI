package fr.istic.taa.jaxrs.service;

import fr.istic.taa.jaxrs.dao.ChildDAO;
import fr.istic.taa.jaxrs.domain.Child;
import fr.istic.taa.jaxrs.exceptions.ValueAlreadyExistsException;

import java.util.List;

public class ChildService {

    public ChildDAO childDAO;

    public ChildService() {
        this.childDAO = new ChildDAO();
    }
    public void createChild(Child child) throws ValueAlreadyExistsException {
        childDAO.createChild(child);
    }

    public List<Child> getChildByName(String firstName, String lastName) {
        return childDAO.getChildByName(firstName, lastName);
    }

    public List<Child> getChildList() {
        return childDAO.getChildList();
    }
}
