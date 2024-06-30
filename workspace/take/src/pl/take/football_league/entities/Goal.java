package pl.take.football_league.entities;

import javax.persistence.*;

@Entity
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    long id;
    int minute;
    boolean ownGoal;
    @ManyToOne
    Player scorer;
    @ManyToOne
    Player assistant;
    @ManyToOne
    Game game;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public Player getScorer() {
        return scorer;
    }

    public void setScorer(Player scorer) {
        this.scorer = scorer;
    }

    public Player getAssistant() {
        return assistant;
    }

    public void setAssistant(Player assistant) {
        this.assistant = assistant;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
