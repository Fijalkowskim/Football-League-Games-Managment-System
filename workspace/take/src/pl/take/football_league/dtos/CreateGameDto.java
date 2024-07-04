package pl.take.football_league.dtos;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class CreateGameDto {
    Date date;
    Integer awayResult;
    Integer homeResult;
    String location;
    Boolean played;
    Long homeClubId;
    Long awayClubId;
    List<Integer> players = new ArrayList<Integer>();

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

	public Boolean isPlayed() {
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

	public List<Integer> getPlayers() {
		return players;
	}

	public void setPlayers(List<Integer> players) {
		this.players = players;
	}
}
