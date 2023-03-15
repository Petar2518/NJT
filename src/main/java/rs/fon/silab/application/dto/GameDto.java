/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.silab.application.dto;

import java.util.Objects;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



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

    public GameDto() {
    }

    public GameDto(Long gameId, TeamDto homeTeam, TeamDto awayTeam, int homeTeamGoals, int awayTeamGoals) {
        this.gameId = gameId;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeTeamGoals = homeTeamGoals;
        this.awayTeamGoals = awayTeamGoals;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.gameId);
        hash = 53 * hash + Objects.hashCode(this.homeTeam);
        hash = 53 * hash + Objects.hashCode(this.awayTeam);
        hash = 53 * hash + this.homeTeamGoals;
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
        return Objects.equals(this.awayTeam, other.awayTeam);
    }

    @Override
    public String toString() {
        return "GameDto{" + "gameId=" + gameId + ", homeTeam=" + homeTeam + ", awayTeam=" + awayTeam + ", homeTeamGoals=" + homeTeamGoals + ", awayTeamGoals=" + awayTeamGoals + '}';
    }


    
    
}
