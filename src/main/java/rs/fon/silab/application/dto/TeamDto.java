/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.silab.application.dto;

import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.validation.constraints.NotNull;


/**
 *
 * @author gg
 */
public class TeamDto implements Dto{
    @NotNull(message= "Team name is required")
    private String teamName;
    @NotNull(message= "Country is required")
    private String country;
    @NotNull(message= "City is required")
    private String city;
    private List<PlayerDto> players;
    

    public TeamDto() {
    }

    public TeamDto(String teamName, String country, String city, List<PlayerDto> players) {
        this.teamName = teamName;
        this.country = country;
        this.city = city;
        this.players = players;
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
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.teamName);
        hash = 59 * hash + Objects.hashCode(this.country);
        hash = 59 * hash + Objects.hashCode(this.city);
        hash = 59 * hash + Objects.hashCode(this.players);
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
        return Objects.equals(this.players, other.players);
    }

    @Override
    public String toString() {
        return "TeamDto{" + "teamName=" + teamName + ", country=" + country + ", city=" + city + ", players=" + players + '}';
    }
    


   

    

    
    
}
