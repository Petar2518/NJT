/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.silab.application.converter;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import rs.fon.silab.application.dto.PlayerDto;
import rs.fon.silab.application.dto.TeamDto;
import rs.fon.silab.application.model.TeamEntity;

/**
 *
 * @author gg
 */
@Component
public class TeamConverter implements GenericConverter<TeamDto, TeamEntity>{
    


    @Override
    public TeamEntity toEntity(TeamDto d) {
        return new TeamEntity(d.getTeamId(),d.getTeamName(), d.getCountry(), d.getCity());
    }

   public TeamEntity toEntityIdOnly(TeamDto d){
        return new TeamEntity(d.getTeamId());
    }


    @Override
    public TeamDto toDto(TeamEntity e) {
        List<PlayerDto> players = new ArrayList<>();
        return new TeamDto(e.getTeamId(),e.getTeamName(),e.getCountry(),e.getCity(),players);
    }

    
}
