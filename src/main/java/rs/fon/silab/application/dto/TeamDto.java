/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.silab.application.dto;

import java.util.List;
import java.util.Objects;
import javax.validation.constraints.NotNull;

/**
 *
 * @author gg
 */
public class TeamDto implements Dto {

    @NotNull(message = "Team ID is required")
    private Long teamId;
    @NotNull(message = "Team name is required")
    private String teamName;
    private String country;
    private String city;
    private List<PlayerDto> players;

    public TeamDto() {
    }

    public TeamDto(Long teamId) {
        this.teamId = teamId;
    }

    public TeamDto(Long teamId, String teamName, String country, String city, List<PlayerDto> players) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.country = country;
        this.city = city;
        this.players = players;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<PlayerDto> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerDto> players) {
        this.players = players;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.teamId);
        hash = 71 * hash + Objects.hashCode(this.teamName);
        hash = 71 * hash + Objects.hashCode(this.country);
        hash = 71 * hash + Objects.hashCode(this.city);
        hash = 71 * hash + Objects.hashCode(this.players);
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
        final TeamDto other = (TeamDto) obj;
        if (!Objects.equals(this.teamName, other.teamName)) {
            return false;
        }
        if (!Objects.equals(this.country, other.country)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.teamId, other.teamId)) {
            return false;
        }
        return Objects.equals(this.players, other.players);
    }

    @Override
    public String toString() {
        return "TeamDto{" + "teamId=" + teamId + ", teamName=" + teamName + ", country=" + country + ", city=" + city + ", players=" + players + '}';
    }

}
