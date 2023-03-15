/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.silab.application.dto;

import java.util.Objects;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import rs.fon.silab.application.Enums.PositionEnum;
import rs.fon.silab.application.model.TeamEntity;

/**
 *
 * @author gg
 */
public class PlayerDto implements Dto{
    @NotNull(message= "Player ID is required")
    private Long playerId;
    @NotNull(message= "Home team is required")
    private String name;
    private PositionEnum position;
    private TeamDto team;
    private int age;

    public PlayerDto() {
    }

    public PlayerDto(Long playerId, String name, String position, TeamDto team, int age) {
        this.playerId = playerId;
        this.name = name;
        this.position = PositionEnum.valueOf(position);
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
        return position.name();
    }

    public void setPosition(String position) {
        this.position = PositionEnum.valueOf(position);
    }

    public TeamDto getTeam() {
        return team;
    }

    public void setTeam(TeamDto team) {
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
        hash = 37 * hash + Objects.hashCode(this.playerId);
        hash = 37 * hash + Objects.hashCode(this.name);
        hash = 37 * hash + Objects.hashCode(this.position);
        hash = 37 * hash + Objects.hashCode(this.team);
        hash = 37 * hash + this.age;
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
        final PlayerDto other = (PlayerDto) obj;
        if (this.age != other.age) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.playerId, other.playerId)) {
            return false;
        }
        if (this.position != other.position) {
            return false;
        }
        return Objects.equals(this.team, other.team);
    }

    @Override
    public String toString() {
        return "PlayerDto{" + "playerId=" + playerId + ", name=" + name + ", position=" + position + ", team=" + team + ", age=" + age + '}';
    }
    
    


}
