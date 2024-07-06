package pl.take.football_league.ejb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pl.take.football_league.GameDateComparator;
import pl.take.football_league.Mapper;
import pl.take.football_league.Pair;
import pl.take.football_league.dtos.*;
import pl.take.football_league.entities.*;

@Stateless
public class LeagueEJB {

	@PersistenceContext(name="komis")
	EntityManager em;
	
	Mapper mapper = new Mapper();
	
	public Pair<Integer, List<ReturnRecordTableDto>> getTable() {
		System.out.println("Getting league table!");
		@SuppressWarnings("unchecked")
		List<Club> clubList = em.createQuery("select c from Club c").getResultList();
		System.out.println("Returning league table!");
		return new Pair<Integer, List<ReturnRecordTableDto>>(200, null);
	}
	
	public Pair<Integer, List<ReturnGameDto>> getSchedule() {
		System.out.println("Getting league schedule!");
		@SuppressWarnings("unchecked")
		List<Game> matchList = em.createQuery("select g from Game g").getResultList();
		if(!matchList.isEmpty()) Collections.sort(matchList, new GameDateComparator());
		List<ReturnGameDto> matchListDto = new ArrayList<ReturnGameDto>();
		for(int i = 0; i < matchList.size(); i++)
			matchListDto.add(mapper.mapToReturnGameDto(matchList.get(i)));
		System.out.println("Returning league schedule!");
		return new Pair<Integer, List<ReturnGameDto>>(200, matchListDto);
	}
}
