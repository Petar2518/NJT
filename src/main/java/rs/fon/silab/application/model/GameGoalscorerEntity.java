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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    
    @Id
    @Column(name="gg_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ggId;
    @ManyToOne
    @JoinColumn(name="game_id")
    private GameEntity game;
    @ManyToOne
    @JoinColumn(name="player_id")
    private PlayerEntity player;
    @Column(name="goals")
    private int goals;

    public GameGoalscorerEntity() {
    }

    public GameGoalscorerEntity(Long ggId, GameEntity game, PlayerEntity player, int goals) {
        this.ggId = ggId;
        this.game = game;
        this.player = player;
        this.goals = goals;
    }

    public Long getGgId() {
        return ggId;
    }

    public void setGgId(Long ggId) {
        this.ggId = ggId;
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
        hash = 79 * hash + Objects.hashCode(this.ggId);
        hash = 79 * hash + Objects.hashCode(this.game);
        hash = 79 * hash + Objects.hashCode(this.player);
        hash = 79 * hash + this.goals;
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
        if (!Objects.equals(this.ggId, other.ggId)) {
            return false;
        }
        if (!Objects.equals(this.game, other.game)) {
            return false;
        }
        return Objects.equals(this.player, other.player);
    }

    @Override
    public String toString() {
        return "GameGoalscorerEntity{" + "ggId=" + ggId + ", game=" + game + ", player=" + player + ", goals=" + goals + '}';
    }
    

}