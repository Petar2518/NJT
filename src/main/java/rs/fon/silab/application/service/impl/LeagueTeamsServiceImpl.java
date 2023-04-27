/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.silab.application.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import rs.fon.silab.application.converter.LeagueTeamsConverter;
import rs.fon.silab.application.dto.LeagueTeamsDto;
import rs.fon.silab.application.exception.EntityDoesntExistException;
import rs.fon.silab.application.exception.EntityExistsException;
import rs.fon.silab.application.model.LeagueTeamsEntity;
import rs.fon.silab.application.repository.LeagueTeamsRepository;
import rs.fon.silab.application.service.LeagueTeamsService;

/**
 *
 * @author gg
 */
@Service

public class LeagueTeamsServiceImpl implements LeagueTeamsService{
    private final LeagueTeamsRepository leagueTeamsRepository;
    private final LeagueTeamsConverter leagueTeamsConverter;

    public LeagueTeamsServiceImpl(LeagueTeamsRepository leagueTeamsRepository, LeagueTeamsConverter leagueTeamsConverter) {
        this.leagueTeamsRepository = leagueTeamsRepository;
        this.leagueTeamsConverter = leagueTeamsConverter;
    }
    
   

    @Override
    public LeagueTeamsDto save(LeagueTeamsDto ltDto) throws EntityExistsException {
        LeagueTeamsEntity.ltId id = new LeagueTeamsEntity.ltId(ltDto.getLeague().getLeagueId(), ltDto.getTeam().getTeamId());
        Optional<LeagueTeamsEntity> entity = leagueTeamsRepository.findById(id);
        if (entity.isPresent()){
            throw new EntityExistsException(entity, "Given team is already in given league.");
        }
        return leagueTeamsConverter.toDto(leagueTeamsRepository.save(leagueTeamsConverter.toEntity(ltDto)));
    }

    @Override
    public List<LeagueTeamsDto> findAllTeams(Long leagueId) {
        List<LeagueTeamsEntity> teams = leagueTeamsRepository.findAllByIdLeagueId(leagueId);
        return teams.stream().map((entity)->{
            return leagueTeamsConverter.toDto(entity);
        }).collect(Collectors.toList());
        
    }

    @Override
    public List<LeagueTeamsDto> findAllLeagues(Long teamId) {
        List<LeagueTeamsEntity> teams = leagueTeamsRepository.findAllByIdTeamId(teamId);
        return teams.stream().map((entity)->{
            return leagueTeamsConverter.toDto(entity);
        }).collect(Collectors.toList());
    }

    @Override
    public void delete(Long leagueId, Long teamId) {
        LeagueTeamsEntity.ltId id = new LeagueTeamsEntity.ltId(leagueId, teamId);
        leagueTeamsRepository.deleteById(id);
    }

    @Override
    public LeagueTeamsDto update(LeagueTeamsDto ltDto, Long leagueId, Long teamId) throws EntityDoesntExistException{
        LeagueTeamsEntity.ltId id = new LeagueTeamsEntity.ltId(leagueId, teamId);
        Optional<LeagueTeamsEntity> entity = leagueTeamsRepository.findById(id);
        if (entity.isEmpty()){
            throw new EntityDoesntExistException(entity, "Given team is already in given league.");
        }
        LeagueTeamsDto lt2Dto = leagueTeamsConverter.toDto(entity.get());
        lt2Dto.setPoints(ltDto.getPoints());
        return leagueTeamsConverter.toDto(leagueTeamsRepository.save(leagueTeamsConverter.toEntity(lt2Dto)));
        
    }

    @Override
    public Optional<LeagueTeamsDto> findByLeagueTeam(Long leagueId, Long teamId) {
        LeagueTeamsEntity.ltId id = new LeagueTeamsEntity.ltId(leagueId, teamId);
         Optional<LeagueTeamsEntity> entity = leagueTeamsRepository.findById(id);
        if (entity.isPresent()){
            return Optional.of(leagueTeamsConverter.toDto(entity.get()));
        }
        return Optional.empty();
    }

    @Override
    public List<LeagueTeamsDto> findAll() {
        List<LeagueTeamsEntity> participants = leagueTeamsRepository.findAll();
        return participants.stream().map((entity)->{
        return leagueTeamsConverter.toDto(entity);
    }).collect(Collectors.toList());
    }

    
}
