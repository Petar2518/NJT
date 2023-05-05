/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.silab.application.dto;

import java.util.Objects;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 *
 * @author gg
 */
public class GameGoalscorerDto implements Dto {

    @NotNull(message = "Game is required")
    private GameDto game;
    @NotNull(message = "Player is required")
    private PlayerDto player;
    @NotNull(message = "Number of goals is required")
    @Min(value = 1, message = "Player must score more than 0 goals")
    private int goals;
    @NotNull(message = "Team is required")
    private TeamDto team;

    public GameGoalscorerDto(GameDto game, PlayerDto player, int goals, TeamDto team) {
        this.game = game;
        this.player = player;
        this.goals = goals;
        this.team = team;
    }

    public GameGoalscorerDto() {
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

    public TeamDto getTeam() {
        return team;
    }

    public void setTeam(TeamDto team) {
        this.team = team;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.game);
        hash = 59 * hash + Objects.hashCode(this.player);
        hash = 59 * hash + this.goals;
        hash = 59 * hash + Objects.hashCode(this.team);
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
        if (!Objects.equals(this.game, other.game)) {
            return false;
        }
        if (!Objects.equals(this.player, other.player)) {
            return false;
        }
        return Objects.equals(this.team, other.team);
    }

    @Override
    public String toString() {
        return "GameGoalscorerDto{" + "game=" + game + ", player=" + player + ", goals=" + goals + ", team=" + team + '}';
    }

}
