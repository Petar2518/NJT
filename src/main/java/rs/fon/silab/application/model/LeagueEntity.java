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
/**
 *
 * @author gg
 */
@Entity
@Table(name="league")
public class LeagueEntity implements Serializable, rs.fon.silab.application.model.Entity{
    @Id
    @Column(name = "league_id")
    private Long leagueId;
    @Column(name = "name")
    private String leagueName;
    @Column(name = "nation")
    private String leagueNation;
    @Column(name = "division")
    private String leagueDivision;

    public LeagueEntity() {
    }

    public LeagueEntity(Long leagueId, String leagueName, String leagueNation, String leagueDivision) {
        this.leagueId = leagueId;
        this.leagueName = leagueName;
        this.leagueNation = leagueNation;
        this.leagueDivision = leagueDivision;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.leagueId);
        hash = 37 * hash + Objects.hashCode(this.leagueName);
        hash = 37 * hash + Objects.hashCode(this.leagueNation);
        hash = 37 * hash + Objects.hashCode(this.leagueDivision);
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
        final LeagueEntity other = (LeagueEntity) obj;
        if (!Objects.equals(this.leagueName, other.leagueName)) {
            return false;
        }
        if (!Objects.equals(this.leagueNation, other.leagueNation)) {
            return false;
        }
        if (!Objects.equals(this.leagueDivision, other.leagueDivision)) {
            return false;
        }
        return Objects.equals(this.leagueId, other.leagueId);
    }

    @Override
    public String toString() {
        return "LeagueEntity{" + "leagueId=" + leagueId + ", leagueName=" + leagueName + ", leagueNation=" + leagueNation + ", leagueDivision=" + leagueDivision + '}';
    }

   

    
}
