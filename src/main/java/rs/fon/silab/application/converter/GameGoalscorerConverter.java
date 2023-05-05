/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.silab.application.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rs.fon.silab.application.dto.GameGoalscorerDto;
import rs.fon.silab.application.model.GameGoalscorerEntity;

/**
 *
 * @author gg
 */
@Component
public class GameGoalscorerConverter implements GenericConverter<GameGoalscorerDto, GameGoalscorerEntity> {

    private final GameConverter gc;
    private final PlayerConverter pc;
    private final TeamConverter tc;

    @Autowired
    public GameGoalscorerConverter(GameConverter gc, PlayerConverter pc, TeamConverter tc) {
        this.gc = gc;
        this.pc = pc;
        this.tc = tc;
    }

    @Override
    public GameGoalscorerEntity toEntity(GameGoalscorerDto d) {

        GameGoalscorerEntity.ggId ggid = new GameGoalscorerEntity.ggId(d.getGame().getGameId(), d.getPlayer().getPlayerId());
        return new GameGoalscorerEntity(ggid, gc.toEntityIdOnly(d.getGame()), pc.toEntityIdOnly(d.getPlayer()), d.getGoals(), tc.toEntityIdOnly(d.getTeam()));
    }

    @Override
    public GameGoalscorerDto toDto(GameGoalscorerEntity e) {

        return new GameGoalscorerDto(gc.toDto(e.getGame()), pc.toDto(e.getPlayer()), e.getGoals(), tc.toDto(e.getTeam()));

    }

}
