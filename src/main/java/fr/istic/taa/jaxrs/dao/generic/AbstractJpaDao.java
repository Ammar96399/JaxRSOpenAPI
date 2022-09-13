package fr.istic.taa.jaxrs.dao.generic;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.ws.rs.NotFoundException;

public abstract class AbstractJpaDao<K, T extends Serializable> implements IGenericDao<K, T> {

	private Class<T> clazz;

	protected EntityManager manager;

	public AbstractJpaDao(Class<T> clazz) {
		this.clazz = clazz;
		this.manager = EntityManagerHelper.getEntityManager();
	}

	public T findOne(K id) {
		return manager.find(clazz, id);
	}

	public List<T> findAll() {
		return manager.createQuery("select e from " + clazz.getName() + " as e",clazz).getResultList();
	}

	public void save(T entity) {
		EntityTransaction t = this.manager.getTransaction();
		t.begin();
		manager.persist(entity);
		t.commit();

	}

	public T update(final T entity) {
		EntityTransaction t = this.manager.getTransaction();
		t.begin();
		T res = manager.merge(entity);
		t.commit();
		return res;

	}

	public List<T> getAll() {
		return this.manager
				.createQuery("select obj from " + this.clazz.getName() + " obj", clazz)
				.getResultList();
	}

	public T getById(K id) throws NotFoundException {
		var res = this.manager.find(this.clazz, id);
		if (Objects.nonNull(res)) {
			return res;
		} else {
			throw new NotFoundException("Cannot find the requested resource bound by id");
		}
	}

	public boolean exists(K id) {
		return this.manager.find(this.clazz, id) != null;
	}

	public void delete(T entity) {
		EntityTransaction t = this.manager.getTransaction();
		t.begin();
		manager.remove(entity);
		t.commit();

	}

	public void deleteById(K entityId) {
		T entity = findOne(entityId);
		delete(entity);
	}
}
