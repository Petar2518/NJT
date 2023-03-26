/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.silab.application.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;
import javax.persistence.OneToMany;
/**
 *
 * @author gg
 */
@Entity
@Table(name="team")
public class TeamEntity implements Serializable, rs.fon.silab.application.model.Entity{
     @Id
    @Column(name="name")
    private String teamName;
    @Column
    private String country;
    @Column 
    private String city;
    @OneToMany(mappedBy = "team")
    List<PlayerEntity> players;


    public TeamEntity() {
    }

    public TeamEntity(String teamName, String country, String city) {
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

    public List<PlayerEntity> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerEntity> players) {
        this.players = players;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.teamName);
        hash = 67 * hash + Objects.hashCode(this.country);
        hash = 67 * hash + Objects.hashCode(this.city);
        hash = 67 * hash + Objects.hashCode(this.players);
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
        final TeamEntity other = (TeamEntity) obj;
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
        return "TeamEntity{" + "teamName=" + teamName + ", country=" + country + ", city=" + city + ", players=" + players + '}';
    }

    
    
    
    
    
}
