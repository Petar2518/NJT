/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.silab.application.dto;

import java.util.Objects;
import javax.validation.constraints.NotNull;



/**
 *
 * @author gg
 */
public class GameDto implements Dto{
    @NotNull(message= "Game ID is required")
    private Long gameId;
    @NotNull(message= "Home team is required")
    private TeamDto homeTeam;
    @NotNull(message= "Away team is required")
    private TeamDto awayTeam;
    @NotNull(message= "Number of goals scored by home team is required")
    private int homeTeamGoals;
    @NotNull(message= "Number of goals scored by away team is required")
    private int awayTeamGoals;
    private LeagueDto league;

    public GameDto() {
    }

    public GameDto(String gameId) {
        this.gameId = Long.parseLong(gameId);
    }

    public GameDto(Long gameId, TeamDto homeTeam, TeamDto awayTeam, int homeTeamGoals, int awayTeamGoals, LeagueDto league) {
        this.gameId = gameId;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeTeamGoals = homeTeamGoals;
        this.awayTeamGoals = awayTeamGoals;
        this.league = league;
    }


    

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public TeamDto getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(TeamDto homeTeam) {
        this.homeTeam = homeTeam;
    }

    public TeamDto getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(TeamDto awayTeam) {
        this.awayTeam = awayTeam;
    }

    public int getHomeTeamGoals() {
        return homeTeamGoals;
    }

    public void setHomeTeamGoals(int homeTeamGoals) {
        this.homeTeamGoals = homeTeamGoals;
    }

    public int getAwayTeamGoals() {
        return awayTeamGoals;
    }

    public void setAwayTeamGoals(int awayTeamGoals) {
        this.awayTeamGoals = awayTeamGoals;
    }

    public LeagueDto getLeague() {
        return league;
    }

    public void setLeague(LeagueDto league) {
        this.league = league;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.gameId);
        hash = 59 * hash + Objects.hashCode(this.homeTeam);
        hash = 59 * hash + Objects.hashCode(this.awayTeam);
        hash = 59 * hash + this.homeTeamGoals;
        hash = 59 * hash + this.awayTeamGoals;
        hash = 59 * hash + Objects.hashCode(this.league);
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
        final GameDto other = (GameDto) obj;
        if (this.homeTeamGoals != other.homeTeamGoals) {
            return false;
        }
        if (this.awayTeamGoals != other.awayTeamGoals) {
            return false;
        }
        if (!Objects.equals(this.gameId, other.gameId)) {
            return false;
        }
        if (!Objects.equals(this.homeTeam, other.homeTeam)) {
            return false;
        }
        if (!Objects.equals(this.awayTeam, other.awayTeam)) {
            return false;
        }
        return Objects.equals(this.league, other.league);
    }

    @Override
    public String toString() {
        return "GameDto{" + "gameId=" + gameId + ", homeTeam=" + homeTeam + ", awayTeam=" + awayTeam + ", homeTeamGoals=" + homeTeamGoals + ", awayTeamGoals=" + awayTeamGoals + ", league=" + league + '}';
    }

    



    


    
    
}
