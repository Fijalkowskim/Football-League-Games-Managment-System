package pl.take.football_league.dtos;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import pl.take.football_league.entities.Player;

public class UpdateGameDto {
	Date date;
    Integer awayResult;
    Integer homeResult;
    String location;
    Boolean played;
    Long homeClubId;
    Long awayClubId;
    Set<Player> players = new HashSet();
    
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Integer getAwayResult() {
		return awayResult;
	}
	
	public void setAwayResult(Integer awayResult) {
		this.awayResult = awayResult;
	}
	
	public Integer getHomeResult() {
		return homeResult;
	}
	
	public void setHomeResult(Integer homeResult) {
		this.homeResult = homeResult;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public Boolean getPlayed() {
		return played;
	}
	
	public void setPlayed(Boolean played) {
		this.played = played;
	}
	
	public Long getHomeClubId() {
		return homeClubId;
	}
	
	public void setHomeClubId(Long homeClubId) {
		this.homeClubId = homeClubId;
	}
	
	public Long getAwayClubId() {
		return awayClubId;
	}
	
	public void setAwayClubId(Long awayClubId) {
		this.awayClubId = awayClubId;
	}
	
	public Set<Player> getPlayers() {
		return players;
	}
	
	public void setPlayers(Set<Player> players) {
		this.players = players;
	}
}
