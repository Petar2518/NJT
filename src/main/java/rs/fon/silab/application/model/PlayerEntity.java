/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.silab.application.model;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import rs.fon.silab.application.util.PositionEnum;
import javax.persistence.Table;

/**
 *
 * @author gg
 */
@Entity
@Table(name="player")
public class PlayerEntity implements Serializable,rs.fon.silab.application.model.Entity{
    @Id
    @Column(name="player_id")
    private Long playerId;
    @Column
    private String name;
    @Enumerated(EnumType.STRING)
    @Column
    private PositionEnum position;
    @ManyToOne
    @JoinColumn(name= "team", referencedColumnName = "name")
    private TeamEntity team;
    @Column
    private int age;

    public PlayerEntity() {
    }

    public PlayerEntity(Long playerId, String name, String position, TeamEntity team, int age) {
        this.playerId = playerId;
        this.name = name;
        this.position=PositionEnum.valueOf(position);
        this.team = team;
        this.age = age;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return this.position.name();
    }

    public void setPosition(String position) {
        this.position = PositionEnum.valueOf(position);
    }

    public TeamEntity getTeam() {
        return team;
    }

    public void setTeam(TeamEntity team) {
        this.team = team;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.playerId);
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.position);
        hash = 97 * hash + Objects.hashCode(this.team);
        hash = 97 * hash + this.age;
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
        final PlayerEntity other = (PlayerEntity) obj;
        if (this.age != other.age) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.team, other.team)) {
            return false;
        }
        if (!Objects.equals(this.playerId, other.playerId)) {
            return false;
        }
        return this.position == other.position;
    }

    @Override
    public String toString() {
        return "PlayerEntity{" + "playerId=" + playerId + ", name=" + name + ", position=" + position + ", team=" + team + ", age=" + age + '}';
    }
    
      
    
}

