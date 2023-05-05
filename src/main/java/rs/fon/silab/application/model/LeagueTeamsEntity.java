/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.silab.application.model;

/**
 *
 * @author gg
 */
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
@Table(name = "leagueteams")

public class LeagueTeamsEntity implements Serializable, rs.fon.silab.application.model.Entity {

    @EmbeddedId
    ltId id;
    @ManyToOne
    @MapsId("leagueId")
    @JoinColumn(name = "league_id")
    private LeagueEntity league;
    @ManyToOne
    @MapsId("teamId")
    @JoinColumn(name = "team")
    private TeamEntity team;
    @Column(name = "points")
    private int points;

    public LeagueTeamsEntity() {
    }

    public LeagueTeamsEntity(ltId id, LeagueEntity league, TeamEntity team, int points) {
        this.id = id;
        this.league = league;
        this.team = team;
        this.points = points;
    }

    public ltId getId() {
        return id;
    }

    public void setId(ltId id) {
        this.id = id;
    }

    public LeagueEntity getLeague() {
        return league;
    }

    public void setLeague(LeagueEntity league) {
        this.league = league;
    }

    public TeamEntity getTeam() {
        return team;
    }

    public void setTeam(TeamEntity team) {
        this.team = team;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.league);
        hash = 89 * hash + Objects.hashCode(this.team);
        hash = 89 * hash + this.points;
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
        final LeagueTeamsEntity other = (LeagueTeamsEntity) obj;
        if (this.points != other.points) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.league, other.league)) {
            return false;
        }
        return Objects.equals(this.team, other.team);
    }

    @Override
    public String toString() {
        return "LeagueTeamsEntity{" + "id=" + id + ", league=" + league + ", team=" + team + ", points=" + points + '}';
    }

    @Embeddable
    public static class ltId implements Serializable {

        @Column(name = "league_id")
        private Long leagueId;
        @Column(name = "team")
        private Long teamId;

        public ltId() {
        }

        public ltId(Long leagueId, Long teamId) {
            this.leagueId = leagueId;
            this.teamId = teamId;
        }

        public Long getLeagueId() {
            return leagueId;
        }

        public void setLeagueId(Long leagueId) {
            this.leagueId = leagueId;
        }

        public Long getTeamId() {
            return teamId;
        }

        public void setTeamId(Long teamId) {
            this.teamId = teamId;
        }

        @Override
        public int hashCode() {
            int hash = 3;
            hash = 61 * hash + Objects.hashCode(this.leagueId);
            hash = 61 * hash + Objects.hashCode(this.teamId);
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
            final ltId other = (ltId) obj;
            if (!Objects.equals(this.leagueId, other.leagueId)) {
                return false;
            }
            return Objects.equals(this.teamId, other.teamId);
        }

        @Override
        public String toString() {
            return "ltId{" + "leagueId=" + leagueId + ", teamId=" + teamId + '}';
        }

    }
}
