/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.silab.application.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rs.fon.silab.application.dto.PlayerDto;
import rs.fon.silab.application.model.PlayerEntity;

/**
 *
 * @author gg
 */
@Component
public class PlayerConverter implements GenericConverter<PlayerDto, PlayerEntity>{
    private final TeamConverter tc;
    @Autowired
    public PlayerConverter(TeamConverter tc) {
        this.tc = tc;
    }
    
 
    @Override
    public PlayerEntity toEntity(PlayerDto d) {
        return new PlayerEntity(d.getPlayerId(), d.getName(), d.getPosition(),tc.toEntity(d.getTeam()) ,d.getAge());
    }

    @Override
    public PlayerDto toDto(PlayerEntity e) {
        return new PlayerDto(e.getPlayerId(),e.getName(),e.getPosition(),tc.toDto(e.getTeam()),e.getAge());
    }
    
}
