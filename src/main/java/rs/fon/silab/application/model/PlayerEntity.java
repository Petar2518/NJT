/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.silab.application.model;

import javax.persistence.Entity;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import rs.fon.silab.application.util.PositionEnum;


/**
 *
 * @author gg
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class PlayerEntity extends FootballPersonEntity{
    
    
    @Enumerated(EnumType.STRING)
    @Column
    private PositionEnum position;

    public PlayerEntity() {
    }

    public PlayerEntity(PositionEnum position) {
        super();
        this.position = position;
    }

    public PlayerEntity(Long id) {
        super(id);
    }
    

    public PlayerEntity( Long id, String name,String position, TeamEntity team, int age) {
        super(id, name, team, age);
        this.position = PositionEnum.valueOf(position);
    }

    public PlayerEntity(String position, Long id) {
        super(id);
        this.position = PositionEnum.valueOf(position);
    }

     public String getPosition() {
        return this.position.name();
    }

    public void setPosition(String position) {
        this.position = PositionEnum.valueOf(position);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.position);
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
        return this.position == other.position;
    }

    @Override
    public String toString() {
        return "PlayerEntity{" + "position=" + position + '}';
    }
    
    
    
    
}

