/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.silab.application.converter;

import org.springframework.stereotype.Component;
import rs.fon.silab.application.dto.GameGoalscorerDto;
import rs.fon.silab.application.model.GameGoalscorerEntity;

/**
 *
 * @author gg
 */
@Component
public class GameGoalscorerConverter implements GenericConverter<GameGoalscorerDto,GameGoalscorerEntity>{
    GameConverter gc = new GameConverter();
    PlayerConverter pc = new PlayerConverter();
    @Override
    public GameGoalscorerEntity toEntity(GameGoalscorerDto d) {
        return new GameGoalscorerEntity(d.getId(), gc.toEntity(d.getGame()), pc.toEntity(d.getPlayer()), d.getGoals());
    }

    @Override
    public GameGoalscorerDto toDto(GameGoalscorerEntity e) {
        
        return new GameGoalscorerDto(e.getGgId(), gc.toDto(e.getGame()), pc.toDto(e.getPlayer()), e.getGoals());
        
    }
    
}
