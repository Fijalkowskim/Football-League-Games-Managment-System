package pl.take.football_league.ejb;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pl.take.football_league.Mapper;
import pl.take.football_league.Pair;
import pl.take.football_league.dtos.*;
import pl.take.football_league.entities.*;

public class GameEJB {
	
	@PersistenceContext(name="komis")
	EntityManager em;
	
	Mapper mapper = new Mapper();
	
	public Pair<Integer, List<ReturnGameDto>> getMatches() {
		System.out.println("Getting all matches!");
		@SuppressWarnings("unchecked")
		List<Game> matchList = em.createQuery("select g from Game g").getResultList();
		List<ReturnGameDto> matchDtoList = new ArrayList<ReturnGameDto>();
		for(int i = 0; i < matchList.size(); i++)
			matchDtoList.add(mapper.mapToReturnGameDto(matchList.get(i)));
		System.out.println("Returning all games!");
		return new Pair<Integer, List<ReturnGameDto>>(200, matchDtoList);
	}
	
	public Pair<Integer, ReturnGameDto> getMatch(long idc) {
		System.out.println("Getting match with id = " + idc + "!");
		Game match = em.find(Game.class, idc);
		if(match == null)
		{
			System.out.println("Match with given id does not exist!");
			return new Pair<Integer, ReturnGameDto>(404, null);
		}
		ReturnGameDto matchDto = mapper.mapToReturnGameDto(match);
		System.out.println("Returning game with id = " + idc + "!");
		return new Pair<Integer, ReturnGameDto>(200, matchDto);
	}
	
	public Pair<Integer, ReturnClubDto> getMatchHomeClub(long idc) {
		System.out.println("Getting home club of match with id = " + idc + "!");
		Game match = em.find(Game.class, idc);
		if(match == null)
		{
			System.out.println("Match with given id does not exist!");
			return new Pair<Integer, ReturnClubDto>(404, null);
		}
		Club club = match.getHomeClub();
		ReturnClubDto clubDto = mapper.mapToReturnClubDto(club);
		System.out.println("Returning home club of match with id = " + idc + "!");
		return new Pair<Integer, ReturnClubDto>(200, clubDto);
	}
	
	public Pair<Integer, ReturnClubDto> getMatchAwayClub(long idc) {
		System.out.println("Getting away club of match with id = " + idc + "!");
		Game match = em.find(Game.class, idc);
		if(match == null)
		{
			System.out.println("Match with given id does not exist!");
			return new Pair<Integer, ReturnClubDto>(404, null);
		}
		Club club = match.getAwayClub();
		ReturnClubDto clubDto = mapper.mapToReturnClubDto(club);
		System.out.println("Returning away club of match with id = " + idc + "!");
		return new Pair<Integer, ReturnClubDto>(200, clubDto);
	}
	
	public Pair<Integer, List<ReturnPlayerDto>> getMatchPlayers(long idc) {
		System.out.println("Getting players of match with id = " + idc + "!");
		Game match = em.find(Game.class, idc);
		if(match == null)
		{
			System.out.println("Match with given id does not exist!");
			return new Pair<Integer, List<ReturnPlayerDto>>(404, null);
		}
		Set<Player> players = match.getPlayers();
		List<ReturnPlayerDto> playerDtoList = new ArrayList<ReturnPlayerDto>();
		for(Player player : players)
			playerDtoList.add(mapper.mapToReturnPlayerDto(player));
		System.out.println("Returning players of match with id = " + idc + "!");
		return new Pair<Integer, List<ReturnPlayerDto>>(200, playerDtoList);
	}
	
	public Pair<Integer, List<ReturnGoalDto>> getMatchGoals(long idc) {
		System.out.println("Getting goals of match with id = " + idc + "!");
		Game match = em.find(Game.class, idc);
		if(match == null)
		{
			System.out.println("Match with given id does not exist!");
			return new Pair<Integer, List<ReturnGoalDto>>(404, null);
		}
		Set<Goal> goals = match.getGoals();
		List<ReturnGoalDto> goalDtoList = new ArrayList<ReturnGoalDto>();
		for(Goal goal : goals)
			goalDtoList.add(mapper.mapToReturnGoalDto(goal));
		System.out.println("Returning goals of match with id = " + idc + "!");
		return new Pair<Integer, List<ReturnGoalDto>>(200, goalDtoList);
	}
	
	public Pair<Integer, String> createMatch(CreateGameDto matchDto) {
		System.out.println("Creating match!");
		if(matchDto.getDate() == null || matchDto.getLocation() == null || matchDto.isPlayed() == null ||
				matchDto.getHomeClubId() == null || matchDto.getAwayClubId() == null)
		{
			System.out.println("Dto contains null values!");
			return new Pair<Integer, String>(400, "");
		}
		Game match = mapper.mapToFlatGame(matchDto);
		Club homeClub = em.find(Club.class, matchDto.getHomeClubId());
		if(homeClub == null)
		{
			System.out.println("Home club with given id does not exist!");
			return new Pair<Integer, String>(400, null);
		}
		Club awayClub = em.find(Club.class, matchDto.getAwayClubId());
		if(awayClub == null)
		{
			System.out.println("Away club with given id does not exist!");
			return new Pair<Integer, String>(400, null);
		}
		match.setHomeClub(homeClub);
		Set<Game> homeMatches = homeClub.getHomeMatches();
		homeMatches.add(match);
		homeClub.setHomeMatches(homeMatches);
		match.setAwayClub(awayClub);
		Set<Game> awayMatches = awayClub.getAwayMatches();
		awayMatches.add(match);
		awayClub.setAwayMatches(awayMatches);
		if(!matchDto.getPlayers().isEmpty())
		{
			if(matchDto.getPlayers().size() < 22 || matchDto.getPlayers().size() > 32)
			{
				System.out.println("Players array must contain between 22 and 32 values!");
				return new Pair<Integer, String>(400, null);
			}
			else
			{
				Set<Player> players = new HashSet<Player>();
				int numberOfHomeClubPlayers = 0;
				int numberOfAwayClubPlayers = 0;
				for(int i = 0; i < matchDto.getPlayers().size(); i++)
				{
					if(matchDto.getPlayers().get(i) == null)
					{
						System.out.println("Players array contains null values!");
						return new Pair<Integer, String>(400, null);
					}
					Player player = em.find(Player.class, matchDto.getPlayers().get(i));
					if(player == null)
					{
						System.out.println("Player with id = " + matchDto.getPlayers().get(i) + " does not exist!");
						return new Pair<Integer, String>(400, null);
					}
					if(player.getClub().getId() == match.getHomeClub().getId()) numberOfHomeClubPlayers++;
					else if(player.getClub().getId() == match.getAwayClub().getId()) numberOfAwayClubPlayers++;
					else
					{
						System.out.println("Player with id = " + matchDto.getPlayers().get(i) + " does not play for those clubs!");
						return new Pair<Integer, String>(400, null);
					}
					players.add(player);
				}
				if(numberOfHomeClubPlayers < 11 || numberOfHomeClubPlayers > 16)
				{
					System.out.println("Number of players playing for home club must be between 11 and 16!");
					return new Pair<Integer, String>(400, null);
				}
				if(numberOfAwayClubPlayers < 11 || numberOfAwayClubPlayers > 16)
				{
					System.out.println("Number of players playing for away club must be between 11 and 16!");
					return new Pair<Integer, String>(400, null);
				}
				match.setPlayers(players);
				for(Player player : players)
				{
					Set<Game> matches = player.getMatches();
					matches.add(match);
					player.setMatches(matches);
				}
			}
		}
		em.persist(match);
		long id = match.getId();
		System.out.println("Match created!");
		return new Pair<Integer, String>(201, "/matches/" + id);
	}

	public Pair<Integer, ReturnGameDto> updateMatch(long idc, UpdateGameDto updateMatchDto) {
		System.out.println("Updating match!");
		Game match = em.find(Game.class, idc);
		if(match == null)
		{
			System.out.println("Match with given id does not exist!");
			return new Pair<Integer, ReturnGameDto>(404, null);
		}
		if(updateMatchDto.getDate() != null) match.setDate(updateMatchDto.getDate());
		if(updateMatchDto.getHomeResult() != null) match.setHomeResult(updateMatchDto.getHomeResult());
		if(updateMatchDto.getAwayResult() != null) match.setAwayResult(updateMatchDto.getAwayResult());
		if(updateMatchDto.isPlayed() != null) match.setPlayed(updateMatchDto.isPlayed());
		if(updateMatchDto.getLocation() != null) match.setLocation(updateMatchDto.getLocation());
		if(!updateMatchDto.getPlayers().isEmpty())
		{
			if(updateMatchDto.getPlayers().size() < 22 || updateMatchDto.getPlayers().size() > 32)
			{
				System.out.println("Players array must contain between 22 and 32 values!");
				return new Pair<Integer, ReturnGameDto>(400, null);
			}
			else
			{
				Set<Player> players = new HashSet<Player>();
				int numberOfHomeClubPlayers = 0;
				int numberOfAwayClubPlayers = 0;
				for(int i = 0; i < updateMatchDto.getPlayers().size(); i++)
				{
					if(updateMatchDto.getPlayers().get(i) == null)
					{
						System.out.println("Players array contains null values!");
						return new Pair<Integer, ReturnGameDto>(400, null);
					}
					Player player = em.find(Player.class, updateMatchDto.getPlayers().get(i));
					if(player == null)
					{
						System.out.println("Player with id = " + updateMatchDto.getPlayers().get(i) + " does not exist!");
						return new Pair<Integer, ReturnGameDto>(400, null);
					}
					if(player.getClub().getId() == match.getHomeClub().getId()) numberOfHomeClubPlayers++;
					else if(player.getClub().getId() == match.getAwayClub().getId()) numberOfAwayClubPlayers++;
					else
					{
						System.out.println("Player with id = " + updateMatchDto.getPlayers().get(i) + " does not play for those clubs!");
						return new Pair<Integer, ReturnGameDto>(400, null);
					}
					players.add(player);
				}
				if(numberOfHomeClubPlayers < 11 || numberOfHomeClubPlayers > 16)
				{
					System.out.println("Number of players playing for home club must be between 11 and 16!");
					return new Pair<Integer, ReturnGameDto>(400, null);
				}
				if(numberOfAwayClubPlayers < 11 || numberOfAwayClubPlayers > 16)
				{
					System.out.println("Number of players playing for away club must be between 11 and 16!");
					return new Pair<Integer, ReturnGameDto>(400, null);
				}
				match.setPlayers(players);
				for(Player player : players)
				{
					Set<Game> matches = player.getMatches();
					matches.add(match);
					player.setMatches(matches);
				}
			}
		}
		match = em.merge(match);
		ReturnGameDto returnMatchDto = mapper.mapToReturnGameDto(match);
		System.out.println("Match updated!");
		return new Pair<Integer, ReturnGameDto>(200, returnMatchDto);
	}
	
	public Pair<Integer, String> deleteMatch(long idc) {
		System.out.println("Deleting match!");
		Game match = em.find(Game.class, idc);
		if(match == null)
		{
			System.out.println("Match with given id does not exist!");
			return new Pair<Integer, String>(404, "");
		}
		/*Set<Game> homeGames = game.getHomeClub().getHomeMatches();
		homeGames.remove(game);
		game.getHomeClub().setHomeMatches(homeGames);
		Set<Game> awayGames = game.getAwayClub().getAwayMatches();
		awayGames.remove(game);
		game.getAwayClub().setAwayMatches(awayGames);
		for(Player player : game.getPlayers())
		{
			Set<Game> games = player.getGames();
			games.remove(game);
			player.setGames(games);
		}*/
		em.remove(match);
		System.out.println("Match deleted!");
		return new Pair<Integer, String>(200, "Match deleted.");
	}
}
