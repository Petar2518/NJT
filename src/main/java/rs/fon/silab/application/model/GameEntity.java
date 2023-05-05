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
@Table(name = "game")
public class GameEntity implements Serializable, rs.fon.silab.application.model.Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    private Long gameId;
    @ManyToOne
    @JoinColumn(name = "home_team", referencedColumnName = "team_id")
    private TeamEntity home;
    @Column(name = "home_goals")
    private int homeTeamGoals;
    @ManyToOne
    @JoinColumn(name = "away_team", referencedColumnName = "team_id")
    private TeamEntity away;
    @Column(name = "away_goals")
    private int awayTeamGoals;
    @ManyToOne
    @JoinColumn(name = "league", referencedColumnName = "league_id")
    private LeagueEntity league;

    public GameEntity() {
    }

    public GameEntity(Long gameId) {
        this.gameId = gameId;
    }

    public GameEntity(Long gameId, TeamEntity home, int homeTeamGoals, TeamEntity away, int awayTeamGoals, LeagueEntity league) {
        this.gameId = gameId;
        this.home = home;
        this.homeTeamGoals = homeTeamGoals;
        this.away = away;
        this.awayTeamGoals = awayTeamGoals;
        this.league = league;
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

    public LeagueEntity getLeague() {
        return league;
    }

    public void setLeague(LeagueEntity league) {
        this.league = league;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.gameId);
        hash = 29 * hash + Objects.hashCode(this.home);
        hash = 29 * hash + this.homeTeamGoals;
        hash = 29 * hash + Objects.hashCode(this.away);
        hash = 29 * hash + this.awayTeamGoals;
        hash = 29 * hash + Objects.hashCode(this.league);
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
        if (!Objects.equals(this.away, other.away)) {
            return false;
        }
        return Objects.equals(this.league, other.league);
    }

    @Override
    public String toString() {
        return "GameEntity{" + "gameId=" + gameId + ", home=" + home + ", homeTeamGoals=" + homeTeamGoals + ", away=" + away + ", awayTeamGoals=" + awayTeamGoals + ", league=" + league + '}';
    }

}
