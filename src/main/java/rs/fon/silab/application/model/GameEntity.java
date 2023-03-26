/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.silab.application.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author gg
 */
@Entity
@Table(name="game")
public class GameEntity implements Serializable,rs.fon.silab.application.model.Entity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="game_id")
    private Long gameId;
    @ManyToOne
    @JoinColumn(name= "home_team", referencedColumnName = "name")
    private TeamEntity home;
    @Column (name = "home_goals")
    private int homeTeamGoals;
    @ManyToOne
    @JoinColumn(name="away_team", referencedColumnName = "name")
    private TeamEntity away;
    @Column (name="away_goals")
    private int awayTeamGoals;
    public GameEntity() {
    }

    public GameEntity(Long gameId) {
        this.gameId = gameId;
    }
    

    public GameEntity(Long gameId, TeamEntity home, int homeTeamGoals, TeamEntity away, int awayTeamGoals) {
        this.gameId = gameId;
        this.home = home;
        this.homeTeamGoals = homeTeamGoals;
        this.away = away;
        this.awayTeamGoals = awayTeamGoals;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public TeamEntity getHome() {
        return home;
    }

    public void setHome(TeamEntity home) {
        this.home = home;
    }

    public int getHomeTeamGoals() {
        return homeTeamGoals;
    }

    public void setHomeTeamGoals(int homeTeamGoals) {
        this.homeTeamGoals = homeTeamGoals;
    }

    public TeamEntity getAway() {
        return away;
    }

    public void setAway(TeamEntity away) {
        this.away = away;
    }

    public int getAwayTeamGoals() {
        return awayTeamGoals;
    }

    public void setAwayTeamGoals(int awayTeamGoals) {
        this.awayTeamGoals = awayTeamGoals;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.gameId);
        hash = 53 * hash + Objects.hashCode(this.home);
        hash = 53 * hash + this.homeTeamGoals;
        hash = 53 * hash + Objects.hashCode(this.away);
        hash = 53 * hash + this.awayTeamGoals;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GameEntity other = (GameEntity) obj;
        if (this.homeTeamGoals != other.homeTeamGoals) {
            return false;
        }
        if (this.awayTeamGoals != other.awayTeamGoals) {
            return false;
        }
        if (!Objects.equals(this.gameId, other.gameId)) {
            return false;
        }
        if (!Objects.equals(this.home, other.home)) {
            return false;
        }
        return Objects.equals(this.away, other.away);
    }

    @Override
    public String toString() {
        return "GameEntity{" + "gameId=" + gameId + ", home=" + home + ", homeTeamGoals=" + homeTeamGoals + ", away=" + away + ", awayTeamGoals=" + awayTeamGoals + '}';
    }

   
    
}
