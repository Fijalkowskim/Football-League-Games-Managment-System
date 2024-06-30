package pl.take.football_league.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import pl.take.football_league.EntityManagerFactoryCreator;
import pl.take.football_league.entities.Club;

public class CrudDao {
	//EntityManagerFactory emf = EntityManagerFactoryCreator.getFactory();
	
	@PersistenceContext(name="komis")
	EntityManager em; //= emf.createEntityManager();
	
	public <T> void create(T obj) {
		//em.getTransaction().begin();
		//try {
			em.persist(obj);
			//em.getTransaction().commit();
		//}
		//catch(Exception e){}
		//finally{
			//em.close();
		//}
	}
	
	public <T> void update(T obj) {
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
	
	public <T> T get(Class<T> type, long id) {
		T entity = em.find(type, id);
		em.close();
		return entity;
	}
}
