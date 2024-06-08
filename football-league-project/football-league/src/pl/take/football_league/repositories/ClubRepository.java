package pl.take.football_league.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import pl.take.football_league.EntityManagerFactoryCreator;
import pl.take.football_league.entities.Club;

public class ClubRepository {
	EntityManagerFactory emf = EntityManagerFactoryCreator.getFactory();
	
	public void create(Club club) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			em.persist(club);
			em.getTransaction().commit();
		}
		catch(Exception e){}
		finally{
			em.close();
		}
	}
	
	public void update(Club club) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			em.merge(club);
			em.getTransaction().commit();
		}
		catch(Exception e){}
		finally{
			em.close();
		}
	}

	public void delete(Club club) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			em.remove(club);
			em.getTransaction().commit();
		}
		catch(Exception e){}
		finally{
			em.close();
		}
	}
	
	public Club get(long id) {
		EntityManager em = emf.createEntityManager();
		Club club = em.find(Club.class, id);
		em.close();
		return club;
	}
	
	public List<Club> getAll() {		
		EntityManager em = emf.createEntityManager();
		List<Club> clubs = em.createQuery("select c from clubs c").getResultList();
		em.close();
		return clubs;
	}
}
