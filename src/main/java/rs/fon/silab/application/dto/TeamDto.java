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
public class TeamDto implements Dto{
    @NotNull(message= "Team name is required")
    private String teamName;
    @NotNull(message= "Country is required")
    private String country;
    @NotNull(message= "City is required")
    private String city;

    public TeamDto() {
    }

    public TeamDto(String teamName, String country, String city) {
        this.teamName = teamName;
        this.country = country;
        this.city = city;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.teamName);
        hash = 67 * hash + Objects.hashCode(this.country);
        hash = 67 * hash + Objects.hashCode(this.city);
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
        return Objects.equals(this.city, other.city);
    }

    @Override
    public String toString() {
        return "TeamDto{" + "teamName=" + teamName + ", country=" + country + ", city=" + city + '}';
    }
    
    
}
