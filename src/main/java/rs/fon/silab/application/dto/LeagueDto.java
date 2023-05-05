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
public class LeagueDto implements Dto {

    @NotNull(message = "League ID is required")
    private Long leagueId;
    @NotNull(message = "League name is required")
    private String leagueName;
    private String leagueNation;
    private String leagueDivision;
    private String season;

    public LeagueDto() {
    }

    public LeagueDto(String leagueId) {
        this.leagueId = Long.parseLong(leagueId);
    }

    public LeagueDto(Long leagueId, String leagueName, String leagueNation, String leagueDivision, String season) {
        this.leagueId = leagueId;
        this.leagueName = leagueName;
        this.leagueNation = leagueNation;
        this.leagueDivision = leagueDivision;
        this.season = season;
    }

    public Long getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(Long leagueId) {
        this.leagueId = leagueId;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public String getLeagueNation() {
        return leagueNation;
    }

    public void setLeagueNation(String leagueNation) {
        this.leagueNation = leagueNation;
    }

    public String getLeagueDivision() {
        return leagueDivision;
    }

    public void setLeagueDivision(String leagueDivision) {
        this.leagueDivision = leagueDivision;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.leagueId);
        hash = 71 * hash + Objects.hashCode(this.leagueName);
        hash = 71 * hash + Objects.hashCode(this.leagueNation);
        hash = 71 * hash + Objects.hashCode(this.leagueDivision);
        hash = 71 * hash + Objects.hashCode(this.season);
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
        final LeagueDto other = (LeagueDto) obj;
        if (!Objects.equals(this.leagueName, other.leagueName)) {
            return false;
        }
        if (!Objects.equals(this.leagueNation, other.leagueNation)) {
            return false;
        }
        if (!Objects.equals(this.leagueDivision, other.leagueDivision)) {
            return false;
        }
        if (!Objects.equals(this.season, other.season)) {
            return false;
        }
        return Objects.equals(this.leagueId, other.leagueId);
    }

    @Override
    public String toString() {
        return "LeagueDto{" + "leagueId=" + leagueId + ", leagueName=" + leagueName + ", leagueNation=" + leagueNation + ", leagueDivision=" + leagueDivision + ", season=" + season + '}';
    }

}
