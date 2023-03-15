/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.silab.application.dto;

import java.util.Objects;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import rs.fon.silab.application.model.GameGoalscorerEntity;

/**
 *
 * @author gg
 */
public class GameGoalscorerDto implements Dto {
    
    @NotNull(message="ID is required")
    private Long id;
    @NotNull(message="Game is required")
    private GameDto game;
    @NotNull(message="Player is required")
    private PlayerDto player;
    @NotNull(message="Number of goals is required")
    @Min(value = 1, message="Player must score more than 0 goals")
    private int goals;

    public GameGoalscorerDto() {
    }

    public GameGoalscorerDto(Long id, GameDto game, PlayerDto player, int goals) {
        this.id = id;
        this.game = game;
        this.player = player;
        this.goals = goals;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GameDto getGame() {
        return game;
    }

    public void setGame(GameDto game) {
        this.game = game;
    }

    public PlayerDto getPlayer() {
        return player;
    }

    public void setPlayer(PlayerDto player) {
        this.player = player;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.id);
        hash = 83 * hash + Objects.hashCode(this.game);
        hash = 83 * hash + Objects.hashCode(this.player);
        hash = 83 * hash + this.goals;
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
        final GameGoalscorerDto other = (GameGoalscorerDto) obj;
        if (this.goals != other.goals) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.game, other.game)) {
            return false;
        }
        return Objects.equals(this.player, other.player);
    }

    @Override
    public String toString() {
        return "GameGoalscorerDto{" + "id=" + id + ", game=" + game + ", player=" + player + ", goals=" + goals + '}';
    }
    
    
}
