/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.fon.silab.application.service;

import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;
import rs.fon.silab.application.dto.LeagueTeamsDto;
import rs.fon.silab.application.exception.EntityDoesntExistException;
import rs.fon.silab.application.exception.EntityExistsException;

/**
 *
 * @author gg
 */
@Transactional
public interface LeagueTeamsService {

    List<LeagueTeamsDto> findAll();

    LeagueTeamsDto save(LeagueTeamsDto ltDto) throws EntityExistsException;

    List<LeagueTeamsDto> findAllTeams(Long leagueId);

    List<LeagueTeamsDto> findAllLeagues(Long teamId);

    void delete(Long leagueId, Long teamId);

    LeagueTeamsDto update(LeagueTeamsDto ltDto, Long leagueId, Long teamId) throws EntityDoesntExistException;

    Optional<LeagueTeamsDto> findByLeagueTeam(Long leagueId, Long teamId);
}
