package pl.take.football_league.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pl.take.football_league.dtos.*;
import pl.take.football_league.entities.*;

@Stateless
public class ClubEJB {
	
	@PersistenceContext(name="komis")
	EntityManager em;
	
	public void create(CreateClubDto clubDto) {
		System.out.println("Creating club!");	
		em.persist(mapToClub(clubDto));
		System.out.println("Club created!");
	}

	public void delete(int idc) {
		System.out.println("Deleting club!");
		long id = idc;
		Club club = em.find(Club.class, id);
		em.remove(club);
		System.out.println("Club deleted!");
	}

	public ReturnClubDto find(int idc) {
		long id = idc;
		Club club = em.find(Club.class, id);
		ReturnClubDto clubDto = mapToReturnClubDto(club);
		return clubDto;
	}

	public List<ReturnClubDto> get() {		
		List<Club> clubList = em.createQuery("select c from Club c").getResultList();
		List<ReturnClubDto> clubDtoList = new ArrayList();
		for(int i = 0; i < clubList.size(); i++)
			clubDtoList.add(mapToReturnClubDto(clubList.get(i)));
		return clubDtoList;
	}

	public void update(int idc, UpdateClubDto clubDto) {
		System.out.println("Updating club!");
		long id = idc;
		Club club = em.find(Club.class, id);
		if(clubDto.getName() != null) club.setName(clubDto.getName());
		if(clubDto.getLocation() != null) club.setLocation(clubDto.getLocation());
		if(clubDto.getDateOfCreation() != null) club.setDateOfCreation(clubDto.getDateOfCreation());
		club = em.merge(club);
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
