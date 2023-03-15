/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.silab.application.converter;

import org.springframework.stereotype.Component;
import rs.fon.silab.application.dto.GameDto;
import rs.fon.silab.application.model.GameEntity;
import rs.fon.silab.application.converter.TeamConverter;

/**
 *
 * @author gg
 */
@Component
public class GameConverter implements GenericConverter<GameDto, GameEntity>{
    TeamConverter tc = new TeamConverter();
    @Override
    public GameEntity toEntity(GameDto d) {
       return new GameEntity(d.getGameId(), tc.toEntity(d.getHomeTeam()),d.getHomeTeamGoals(),tc.toEntity(d.getAwayTeam()), d.getAwayTeamGoals());
    }
    @Override
    public GameDto toDto(GameEntity e) {
       return new GameDto(e.getGameId(), tc.toDto(e.getHome()),tc.toDto(e.getAway()),e.getHomeTeamGoals(),e.getAwayTeamGoals());
    }
}
