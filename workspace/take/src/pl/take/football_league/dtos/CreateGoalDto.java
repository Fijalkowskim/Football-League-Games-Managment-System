package pl.take.football_league.dtos;

public class CreateGoalDto {
	int minute;
    boolean ownGoal;
    long scorerId;
    long assistantId;
    long gameId;
    
	public int getMinute() {
		return minute;
	}
	
	public void setMinute(int minute) {
		this.minute = minute;
	}
	
	public boolean isOwnGoal() {
		return ownGoal;
	}
	
	public void setOwnGoal(boolean ownGoal) {
		this.ownGoal = ownGoal;
	}
	
	public long getScorerId() {
		return scorerId;
	}
	
	public void setScorerId(long scorerId) {
		this.scorerId = scorerId;
	}
	
	public long getAssistantId() {
		return assistantId;
	}
	
	public void setAssistantId(long assistantId) {
		this.assistantId = assistantId;
	}
	
	public long getGameId() {
		return gameId;
	}
	
	public void setGameId(long gameId) {
		this.gameId = gameId;
	}
}
