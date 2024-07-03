package pl.take.football_league.dtos;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import pl.take.football_league.entities.Player;

public class CreateGameDto {
    Date date;
    int awayResult;
    int homeResult;
    String location;
    boolean played;
    long homeClubId;
    long awayClubId;
    Set<Player> players = new HashSet();

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getAwayResult() {
        return awayResult;
    }

    public void setAwayResult(int awayResult) {
        this.awayResult = awayResult;
    }

    public int getHomeResult() {
        return homeResult;
    }

    public void setHomeResult(int homeResult) {
        this.homeResult = homeResult;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

	public boolean isPlayed() {
		return played;
	}

	public void setPlayed(boolean played) {
		this.played = played;
	}

	public long getHomeClubId() {
		return homeClubId;
	}

	public void setHomeClubId(long homeClubId) {
		this.homeClubId = homeClubId;
	}

	public long getAwayClubId() {
		return awayClubId;
	}

	public void setAwayClubId(long awayClubId) {
		this.awayClubId = awayClubId;
	}

	public Set<Player> getPlayers() {
		return players;
	}

	public void setPlayers(Set<Player> players) {
		this.players = players;
	}
}
