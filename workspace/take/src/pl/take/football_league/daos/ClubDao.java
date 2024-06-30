package pl.take.football_league.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pl.take.football_league.entities.Club;

public class ClubDao {
	
	@PersistenceContext(name="komis")
	EntityManager em;
	
	public List<Club> getAll() {		
		List<Club> clubs = em.createQuery("select c from clubs c").getResultList();
		em.close();
		return clubs;
	}
}
