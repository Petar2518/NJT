/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.silab.application.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import rs.fon.silab.application.converter.LeagueConverter;
import rs.fon.silab.application.dto.LeagueDto;
import rs.fon.silab.application.exception.EntityDoesntExistException;
import rs.fon.silab.application.exception.EntityExistsException;
import rs.fon.silab.application.model.LeagueEntity;
import rs.fon.silab.application.repository.LeagueRepository;
import rs.fon.silab.application.service.LeagueService;

/**
 *
 * @author gg
 */
@Service
public class LeagueServiceImpl implements LeagueService{
    private final LeagueRepository leagueRepository;
    private final LeagueConverter leagueConverter;

    public LeagueServiceImpl(LeagueRepository leagueRepository, LeagueConverter leagueConverter) {
        this.leagueRepository = leagueRepository;
        this.leagueConverter = leagueConverter;
    }

    @Override
    public LeagueDto save(LeagueDto leagueDto) throws EntityExistsException {
        Optional<LeagueEntity> entity = leagueRepository.findById(leagueDto.getLeagueId());
        if (entity.isPresent()){
            throw new EntityExistsException(entity, "League already exists");
        }
        return leagueConverter.toDto(leagueRepository.save(leagueConverter.toEntity(leagueDto)));
    }

    @Override
    public List<LeagueDto> findAll() {
        List<LeagueEntity> leagues = leagueRepository.findAll();
        return leagues.stream().map((entity)->{
            return leagueConverter.toDto(entity);
        }).collect(Collectors.toList());
    }

    @Override
    public List<LeagueDto> findAllByNation(String nation) {
        List<LeagueEntity> leagues = leagueRepository.findAllByLeagueNation(nation);
        return leagues.stream().map((entity)->{
            return leagueConverter.toDto(entity);
        }).collect(Collectors.toList());
    }

    @Override
    public List<LeagueDto> findAllByDivision(String division) {
        List<LeagueEntity> leagues = leagueRepository.findAllByLeagueDivision(division);
        return leagues.stream().map((entity)->{
            return leagueConverter.toDto(entity);
        }).collect(Collectors.toList());
    }

    @Override
    public List<LeagueDto> findByName(String name) {
        List<LeagueEntity> leagues = leagueRepository.findAllByLeagueName(name);
        return leagues.stream().map((entity)->{
            return leagueConverter.toDto(entity);
        }).collect(Collectors.toList());
    }

    @Override
    public Optional<LeagueDto> findById(Long id) {
        Optional<LeagueEntity> entity = leagueRepository.findById(id);
        if (entity.isPresent()){
            return Optional.of(leagueConverter.toDto(entity.get()));
        }
        return Optional.empty();
    }


    @Override
    public void delete(Long id) {
        leagueRepository.deleteById(id);
    }

    @Override
    public LeagueDto updateName(LeagueDto leagueDto, Long id) throws EntityDoesntExistException {
           Optional<LeagueEntity> league = leagueRepository.findById(id);
        if (league.isEmpty()){
            throw new EntityDoesntExistException(leagueDto,"Entity with given id doesnt exist.");
        }
        LeagueDto leagueD=leagueConverter.toDto(league.get());
        leagueD.setLeagueName(leagueDto.getLeagueName());
        return leagueConverter.toDto(leagueRepository.save(leagueConverter.toEntity(leagueD)));
    }
    
    
}
