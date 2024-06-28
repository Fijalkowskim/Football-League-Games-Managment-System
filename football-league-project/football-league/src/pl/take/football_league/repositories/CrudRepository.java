package pl.take.football_league.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import pl.take.football_league.EntityManagerFactoryCreator;
import pl.take.football_league.entities.Club;

public class CrudRepository {
	EntityManagerFactory emf = EntityManagerFactoryCreator.getFactory();
	
	public <T> void create(T obj) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			em.persist(obj);
			em.getTransaction().commit();
		}
		catch(Exception e){}
		finally{
			em.close();
		}
	}
	
	public <T> void update(T obj) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			em.merge(obj);
			em.getTransaction().commit();
		}
		catch(Exception e){}
		finally{
			em.close();
		}
	}

	public void delete(Object obj) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			Object ref = em.merge(obj);
			em.remove(ref);
			em.getTransaction().commit();
		}
		catch(Exception e){}
		finally{
			em.close();
		}
	}
	
	public <T> T get(long id, Class<T> type) {
		EntityManager em = emf.createEntityManager();
		T entity = em.find(type, id);
		em.close();
		return entity;
	}
	
	public List<Club> getAll() {		
		EntityManager em = emf.createEntityManager();
		List<Club> clubs = em.createQuery("select c from clubs c").getResultList();
		em.close();
		return clubs;
	}
}
