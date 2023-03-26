/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.fon.silab.application.service;

import java.util.List;
import java.util.Optional;
import rs.fon.silab.application.dto.LeagueTeamsDto;
import rs.fon.silab.application.exception.EntityDoesntExistException;
import rs.fon.silab.application.exception.EntityExistsException;

/**
 *
 * @author gg
 */
public interface LeagueTeamsService {

    List<LeagueTeamsDto> findAll();

    LeagueTeamsDto save(LeagueTeamsDto ltDto) throws EntityExistsException;

    List<LeagueTeamsDto> findAllTeams(Long leagueId);

    List<LeagueTeamsDto> findAllLeagues(String teamName);

    void delete(Long leagueId, String teamName);

    LeagueTeamsDto update(LeagueTeamsDto ltDto, Long leagueId, String teamName) throws EntityDoesntExistException;

    Optional<LeagueTeamsDto> findByLeagueTeam(Long leagueId, String teamName);
}
