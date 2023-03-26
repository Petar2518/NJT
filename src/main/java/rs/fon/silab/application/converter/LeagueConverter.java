/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.silab.application.converter;

import org.springframework.stereotype.Component;
import rs.fon.silab.application.dto.LeagueDto;
import rs.fon.silab.application.model.LeagueEntity;

/**
 *
 * @author gg
 */
@Component
public class LeagueConverter implements GenericConverter<LeagueDto, LeagueEntity>{
    

    
    @Override
    public LeagueEntity toEntity(LeagueDto d) {
        return new LeagueEntity(d.getLeagueId(), d.getLeagueName(), d.getLeagueNation(), d.getLeagueDivision());
    }

    @Override
    public LeagueDto toDto(LeagueEntity e) {
        return new LeagueDto(e.getLeagueId(), e.getLeagueName(), e.getLeagueNation(), e.getLeagueDivision());
    }
}
