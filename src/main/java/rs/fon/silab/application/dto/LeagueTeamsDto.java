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
public class LeagueTeamsDto implements Dto{
    
    @NotNull(message="League is required")
    private LeagueDto league;
    @NotNull(message="Team is required")
    private TeamDto team;
    @NotNull(message = "Points are required")
    private int points;

    public LeagueTeamsDto() {
    }

    public LeagueTeamsDto(LeagueDto league, TeamDto team, int points) {
        this.league = league;
        this.team = team;
        this.points = points;
    }

    public LeagueDto getLeague() {
        return league;
    }

    public void setLeague(LeagueDto league) {
        this.league = league;
    }

    public TeamDto getTeam() {
        return team;
    }

    public void setTeam(TeamDto team) {
        this.team = team;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.league);
        hash = 59 * hash + Objects.hashCode(this.team);
        hash = 59 * hash + this.points;
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
        final LeagueTeamsDto other = (LeagueTeamsDto) obj;
        if (this.points != other.points) {
            return false;
        }
        if (!Objects.equals(this.league, other.league)) {
            return false;
        }
        return Objects.equals(this.team, other.team);
    }

    @Override
    public String toString() {
        return "LeagueTeamsDto{" + "league=" + league + ", team=" + team + ", points=" + points + '}';
    }
    
    
    
}
