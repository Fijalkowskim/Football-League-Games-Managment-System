package pl.take.football_league.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pl.kurs.komis.Car;
import pl.take.football_league.daos.*;
import pl.take.football_league.dtos.*;
import pl.take.football_league.entities.*;

@Stateless
public class ClubEJB {

	CrudDao crudDao = new CrudDao();
	ClubDao clubDao = new ClubDao();
	
	/*public void create(CreateClubDto clubDto) {
		System.out.println("Creating club!");
		Club club = mapToClub(clubDto);
		crudDao.create(club);
		System.out.println("Club created!");
	}*/
	
	@PersistenceContext(name="komis")
	EntityManager em;

	
	public void create(CreateClubDto clubDto) {
		//em.getTransaction().begin();
		//try {
			
			em.persist(mapToClub(clubDto));
			//em.getTransaction().commit();
		//}
		//catch(Exception e){}
		//finally{
			//em.close();
		//}
	}

	public void delete(int idc) {
		System.out.println("Deleting club!");
		Club club = crudDao.get(Club.class, idc);
		crudDao.delete(club);
		System.out.println("Club deleted!");
	}

	/*public List<Club> findByMake(String make) {
		Query q = manager.createQuery("select c from Club c where c.make like :make");
		q.setParameter("make", make);
		@SuppressWarnings("unchecked")
		List<Car> lista =q.getResultList();
		return lista;
	}*/

	public ReturnClubDto find(int idc) {
		Club club = crudDao.get(Club.class, idc);
		ReturnClubDto clubDto = mapToReturnClubDto(club);
		return clubDto;
	}

	public List<ReturnClubDto> get() {
		List<Club> clubList = clubDao.getAll();
		List<ReturnClubDto> clubDtoList = new ArrayList();
		for(int i = 0; i < clubList.size(); i++)
			clubDtoList.add(mapToReturnClubDto(clubList.get(i)));
		return clubDtoList;
	}

	public void update(int idc, UpdateClubDto clubDto) {
		System.out.println("Updating club!");
		Club club = crudDao.get(Club.class, idc);
		if(clubDto.getName() != null) club.setName(clubDto.getName());
		if(clubDto.getLocation() != null) club.setLocation(clubDto.getLocation());
		if(clubDto.getDateOfCreation() != null) club.setDateOfCreation(clubDto.getDateOfCreation());
		crudDao.update(club);
		System.out.println("Club updated!");
	}

	private ReturnClubDto mapToReturnClubDto(Club club)
	{
		ReturnClubDto clubDto = new ReturnClubDto();
		clubDto.setId(club.getId());
		clubDto.setName(club.getName());
		clubDto.setLocation(club.getLocation());
		clubDto.setDateOfCreation(club.getDateOfCreation());
		clubDto.setPlayers("/clubs/" + club.getId() + "/players");
		clubDto.setHomeMatches("/clubs/" + club.getId() + "/homeMatches");
		clubDto.setAwayMatches("/clubs/" + club.getId() + "/awayMatches");
		return clubDto;
	}
	
	private Club mapToClub(CreateClubDto clubDto)
	{
		Club club = new Club();
		club.setName(clubDto.getName());
		club.setLocation(clubDto.getLocation());
		club.setDateOfCreation(clubDto.getDateOfCreation());
		return club;
	}
}
