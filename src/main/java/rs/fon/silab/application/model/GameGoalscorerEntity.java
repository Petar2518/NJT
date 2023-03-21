/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.silab.application.model;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
/**
 *
 * @author gg
 */
@Entity 
@Table(name = "game_goalscorer")
public class GameGoalscorerEntity implements Serializable,rs.fon.silab.application.model.Entity{
    
    @EmbeddedId
    ggId id;
    @ManyToOne
    @MapsId("gameId")
    @JoinColumn(name="game_id")
    private GameEntity game;
    @ManyToOne
    @MapsId("playerId")
    @JoinColumn(name="player_id")
    private PlayerEntity player;
    @Column(name="goals")
    private int goals;

    public GameGoalscorerEntity() {
    }

    public GameGoalscorerEntity(ggId id, GameEntity game, PlayerEntity player, int goals) {
        this.id = id;
        this.game = game;
        this.player = player;
        this.goals = goals;
    }

    public ggId getId() {
        return id;
    }

    public void setId(ggId id) {
        this.id = id;
    }

    public GameEntity getGame() {
        return game;
    }

    public void setGame(GameEntity game) {
        this.game = game;
    }

    public PlayerEntity getPlayer() {
        return player;
    }

    public void setPlayer(PlayerEntity player) {
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
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.id);
        hash = 43 * hash + Objects.hashCode(this.game);
        hash = 43 * hash + Objects.hashCode(this.player);
        hash = 43 * hash + this.goals;
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
        final GameGoalscorerEntity other = (GameGoalscorerEntity) obj;
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
        return "GameGoalscorerEntity{" + "id=" + id + ", game=" + game + ", player=" + player + ", goals=" + goals + '}';
    }

    @Embeddable
    public static class ggId implements Serializable{
        
        @Column(name= "game_id")
        private Long gameId;
        @Column(name="player_id")
        private Long playerId;

        public ggId() {
        }

        public ggId(Long gameId, Long playerId) {
            this.gameId = gameId;
            this.playerId = playerId;
        }

        public Long getGameId() {
            return gameId;
        }

        public void setGameId(Long gameId) {
            this.gameId = gameId;
        }

        public Long getPlayerId() {
            return playerId;
        }

        public void setPlayerId(Long playerId) {
            this.playerId = playerId;
        }

        @Override
        public int hashCode() {
            int hash = 5;
            hash = 23 * hash + Objects.hashCode(this.gameId);
            hash = 23 * hash + Objects.hashCode(this.playerId);
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
            final ggId other = (ggId) obj;
            if (!Objects.equals(this.gameId, other.gameId)) {
                return false;
            }
            return Objects.equals(this.playerId, other.playerId);
        }
        

    }

}