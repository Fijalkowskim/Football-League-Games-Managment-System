package pl.take.football_league.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pl.take.football_league.entities.Club;

@Stateless
public class ClubEJB {
	
	@PersistenceContext(name="komis")
	EntityManager manager;

	
	public void create(Club club) {
		System.out.println("Creating club!");
		manager.persist(club);
	}

	public void delete(int idc) {
		Club club = manager.find(Club.class, idc);
		manager.remove(club);
	}

	/*public List<Club> findByMake(String make) {
		Query q = manager.createQuery("select c from Club c where c.make like :make");
		q.setParameter("make", make);
		@SuppressWarnings("unchecked")
		List<Car> lista =q.getResultList();
		return lista;
	}*/

	public Club find(int idc) {
		return manager.find(Club.class, idc);
	}

	public List<Club> get() {
		Query q = manager.createQuery("select c from Club c");
		@SuppressWarnings("unchecked")
		List<Club> list = q.getResultList();
		return list;
	}

	public void update(Club club) {
		club = manager.merge(club);
	}

	
}
