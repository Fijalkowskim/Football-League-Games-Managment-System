package pl.take.football_league.ejb;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pl.take.football_league.Mapper;
import pl.take.football_league.Pair;
import pl.take.football_league.dtos.*;
import pl.take.football_league.entities.*;

public class PlayerEJB {
	
	@PersistenceContext(name="komis")
	EntityManager em;
	
	Mapper mapper = new Mapper();
	
	public Pair<Integer, List<ReturnPlayerDto>> getPlayers() {
		System.out.println("Getting all players!");
		List<Player> playerList = em.createQuery("select p from Player p").getResultList();
		List<ReturnPlayerDto> playerDtoList = new ArrayList();
		for(int i = 0; i < playerList.size(); i++)
			playerDtoList.add(mapper.mapToReturnPlayerDto(playerList.get(i)));
		System.out.println("Returning all players!");
		return new Pair<Integer, List<ReturnPlayerDto>>(200, playerDtoList);
	}
	
	public Pair<Integer, ReturnPlayerDto> getPlayer(long idc) {
		System.out.println("Getting player with id = " + idc + "!");
		Player player = em.find(Player.class, idc);
		if(player == null)
		{
			System.out.println("Player with given id does not exist!");
			return new Pair<Integer, ReturnPlayerDto>(404, null);
		}
		ReturnPlayerDto playerDto = mapper.mapToReturnPlayerDto(player);
		System.out.println("Returning player with id = " + idc + "!");
		return new Pair<Integer, ReturnPlayerDto>(200, playerDto);
	}
	
	public Pair<Integer, ReturnClubDto> getPlayerClub(long idc) {
		System.out.println("Getting club of player with id = " + idc + "!");
		Player player = em.find(Player.class, idc);
		if(player == null)
		{
			System.out.println("Player with given id does not exist!");
			return new Pair<Integer, ReturnClubDto>(404, null);
		}
		Club club = player.getClub();
		ReturnClubDto clubDto = mapper.mapToReturnClubDto(club);
		System.out.println("Returning club of player with id = " + idc + "!");
		return new Pair<Integer, ReturnClubDto>(200, clubDto);
	}
	
	public Pair<Integer, List<ReturnGameDto>> getPlayerMatches(long idc) {
		System.out.println("Getting matches of player with id = " + idc + "!");
		Player player = em.find(Player.class, idc);
		if(player == null)
		{
			System.out.println("Player with given id does not exist!");
			return new Pair<Integer, List<ReturnGameDto>>(404, null);
		}
		Set<Game> games = player.getGames();
		List<ReturnGameDto> gameDtoList = new ArrayList();
		for(Game game : games)
			gameDtoList.add(mapper.mapToReturnGameDto(game));
		System.out.println("Returning matches of player with id = " + idc + "!");
		return new Pair<Integer, List<ReturnGameDto>>(200, gameDtoList);
	}
	
	public Pair<Integer, List<ReturnGoalDto>> getPlayerGoals(long idc) {
		System.out.println("Getting goals of player with id = " + idc + "!");
		Player player = em.find(Player.class, idc);
		if(player == null)
		{
			System.out.println("Player with given id does not exist!");
			return new Pair<Integer, List<ReturnGoalDto>>(404, null);
		}
		Set<Goal> goals = player.getGoals();
		List<ReturnGoalDto> goalDtoList = new ArrayList();
		for(Goal goal : goals)
			goalDtoList.add(mapper.mapToReturnGoalDto(goal));
		System.out.println("Returning goals of player with id = " + idc + "!");
		return new Pair<Integer, List<ReturnGoalDto>>(200, goalDtoList);
	}
	
	public Pair<Integer, List<ReturnGoalDto>> getPlayerAssists(long idc) {
		System.out.println("Getting assists of player with id = " + idc + "!");
		Player player = em.find(Player.class, idc);
		if(player == null)
		{
			System.out.println("Player with given id does not exist!");
			return new Pair<Integer, List<ReturnGoalDto>>(404, null);
		}
		Set<Goal> assists = player.getAssists();
		List<ReturnGoalDto> assistDtoList = new ArrayList();
		for(Goal assist : assists)
			assistDtoList.add(mapper.mapToReturnGoalDto(assist));
		System.out.println("Returning assists of player with id = " + idc + "!");
		return new Pair<Integer, List<ReturnGoalDto>>(200, assistDtoList);
	}
	
	public Pair<Integer, String> createPlayer(CreatePlayerDto playerDto) {
		System.out.println("Creating player!");
		if(playerDto.getName() == null || playerDto.getSurname() == null || playerDto.getNumber() == null ||
			playerDto.getPosition() == null || playerDto.getClubId() == null)
		{
			System.out.println("Dto contains null values!");
			return new Pair<Integer, String>(400, "");
		}
		Player player = mapper.mapToFlatPlayer(playerDto);
		Club club = em.find(Club.class, playerDto.getClubId());
		if(club == null)
		{
			System.out.println("Club with given id does not exist!");
			return new Pair<Integer, String>(400, null);
		}
		player.setClub(club);
		Set<Player> players = club.getPlayers();
		players.add(player);
		club.setPlayers(players);
		em.persist(player);
		long id = player.getId();
		System.out.println("Player created!");
		return new Pair<Integer, String>(201, "/players/" + id);
	}

	public Pair<Integer, ReturnPlayerDto> updatePlayer(long idc, UpdatePlayerDto updatePlayerDto) {
		System.out.println("Updating player!");
		Player player = em.find(Player.class, idc);
		if(player == null)
		{
			System.out.println("Player with given id does not exist!");
			return new Pair<Integer, ReturnPlayerDto>(404, null);
		}
		if(updatePlayerDto.getName() != null) player.setName(updatePlayerDto.getName());
		if(updatePlayerDto.getSurname() != null) player.setSurname(updatePlayerDto.getSurname());
		if(updatePlayerDto.getNumber() != null) player.setNumber(updatePlayerDto.getNumber());
		if(updatePlayerDto.getPosition() != null) player.setPosition(updatePlayerDto.getPosition());
		player = em.merge(player);
		ReturnPlayerDto returnPlayerDto = mapper.mapToReturnPlayerDto(player);
		System.out.println("Player updated!");
		return new Pair<Integer, ReturnPlayerDto>(200, returnPlayerDto);
	}
	
	public Pair<Integer, String> deletePlayer(long idc) {
		System.out.println("Deleting player!");
		Player player = em.find(Player.class, idc);
		if(player == null)
		{
			System.out.println("Player with given id does not exist!");
			return new Pair<Integer, String>(404, "");
		}
		em.remove(player);
		System.out.println("Player deleted!");
		return new Pair<Integer, String>(200, "Player deleted");
	}
}