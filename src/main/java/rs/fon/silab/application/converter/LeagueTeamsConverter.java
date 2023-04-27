/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.silab.application.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rs.fon.silab.application.dto.LeagueTeamsDto;
import rs.fon.silab.application.model.LeagueTeamsEntity;

/**
 *
 * @author gg
 */
@Component
public class LeagueTeamsConverter implements GenericConverter<LeagueTeamsDto, LeagueTeamsEntity>{
    private final LeagueConverter lc;
    private final TeamConverter tc;
    
    @Autowired
    public LeagueTeamsConverter(LeagueConverter lc, TeamConverter tc) {
        this.lc = lc;
        this.tc = tc;
    }

    @Override
    public LeagueTeamsEntity toEntity(LeagueTeamsDto d) {
        LeagueTeamsEntity.ltId  id = new LeagueTeamsEntity.ltId(d.getLeague().getLeagueId(), d.getTeam().getTeamId());
        return new LeagueTeamsEntity(id, lc.toEntity(d.getLeague()), tc.toEntity(d.getTeam()), d.getPoints());
    }

    

    @Override
    public LeagueTeamsDto toDto(LeagueTeamsEntity e) {
        return new LeagueTeamsDto(lc.toDto(e.getLeague()),tc.toDto(e.getTeam()), e.getPoints());
    }
    
}
