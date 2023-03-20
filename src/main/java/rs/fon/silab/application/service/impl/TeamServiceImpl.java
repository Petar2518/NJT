package rs.fon.silab.application.service.impl;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import rs.fon.silab.application.converter.TeamConverter;
import rs.fon.silab.application.dto.TeamDto;
import rs.fon.silab.application.exception.EntityDoesntExistException;
import rs.fon.silab.application.exception.EntityExistsException;
import rs.fon.silab.application.model.TeamEntity;
import rs.fon.silab.application.repository.TeamRepository;
import rs.fon.silab.application.service.TeamService;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author gg
 */
@Service
public class TeamServiceImpl implements TeamService{
    private final TeamConverter teamConverter;
    private final TeamRepository teamRepository;

    public TeamServiceImpl(TeamConverter teamConverter, TeamRepository teamRepository) {
        this.teamConverter = teamConverter;
        this.teamRepository = teamRepository;
    }

    @Override
    public TeamDto save(TeamDto teamDto) throws EntityExistsException {
        Optional<TeamEntity> entity = teamRepository.findById(teamDto.getTeamName());
        if (entity.isPresent()){
            throw new EntityExistsException(entity.get(), "Team already exists.");
        }
        
        return teamConverter.toDto(teamRepository.save(teamConverter.toEntity(teamDto)));
    }

    @Override
    public List<TeamDto> findAll() {
        List<TeamEntity> teams = teamRepository.findAll();
        return teams.stream().map((entity)->{
            return teamConverter.toDto(entity);
        }).collect(Collectors.toList());
    }

    @Override
    public List<TeamDto> findAllByCountry(String country) {
        List<TeamEntity> teams = teamRepository.findAllByCountry(country);
        return teams.stream().map((entity)->{
            return teamConverter.toDto(entity);
        }).collect(Collectors.toList());
    }

    @Override
    public List<TeamDto> findAllByCity(String city) {
        List<TeamEntity> teams = teamRepository.findAllByCity(city);
        return teams.stream().map((entity)->{
            return teamConverter.toDto(entity);
        }).collect(Collectors.toList());
    }

    @Override
    public Optional<TeamDto> findByName(String name) {
       Optional<TeamEntity> entity = teamRepository.findById(name);
       if (entity.isPresent()){
           return Optional.of(teamConverter.toDto(entity.get()));
       }
       return Optional.empty();
    }

    @Override
    public void delete(String name) {
        teamRepository.deleteById(name);
    }

    @Override
    public TeamDto updateLocation(TeamDto teamDto, String name) throws EntityDoesntExistException {
         Optional<TeamEntity> team = teamRepository.findById(name);
        if (team.isEmpty()){
            throw new EntityDoesntExistException(teamDto,"Entity with given id doesnt exist.");
        }
        TeamDto teamD=teamConverter.toDto(team.get());
        teamD.setCountry(teamDto.getCountry());
        teamD.setCity(teamDto.getCity());
        return teamConverter.toDto(teamRepository.save(teamConverter.toEntity(teamD)));
    }
    
    
}
