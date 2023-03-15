/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.silab.application.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import rs.fon.silab.application.converter.GameConverter;
import rs.fon.silab.application.dto.GameDto;
import rs.fon.silab.application.exception.EntityExistsException;
import rs.fon.silab.application.model.GameEntity;
import rs.fon.silab.application.repository.GameRepository;
import rs.fon.silab.application.service.GameService;

/**
 *
 * @author gg
 */
@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final GameConverter gameConverter;

    public GameServiceImpl(GameRepository gameRepository, GameConverter gameConverter) {
        this.gameRepository = gameRepository;
        this.gameConverter = gameConverter;
    }

    @Override
    public GameDto save(GameDto gameDto) throws EntityExistsException {
        Optional<GameEntity> entity = gameRepository.findById(gameDto.getGameId());
        if (entity.isPresent()) {
            throw new EntityExistsException(entity.get(), "Game already exists");
        }
        return gameConverter.toDto(gameRepository.save(gameConverter.toEntity(gameDto)));
    }

    @Override
    public List<GameDto> findAll() {
        List<GameEntity> games = gameRepository.findAll();
        return games.stream().map((entity) -> {
            return gameConverter.toDto(entity);
        }).collect(Collectors.toList());
    }

    @Override
    public Optional<GameDto> findById(Long id) {
        Optional<GameEntity> entity = gameRepository.findById(id);
        if (entity.isPresent()) {
            return Optional.of(gameConverter.toDto(entity.get()));
        }
        return Optional.empty();
    }

    @Override
    public List<GameDto> findAllByHomeTeam(String teamName) {
        List<GameEntity> games = gameRepository.findAllByHome_TeamName(teamName);
        return games.stream().map((entity)->{
          return gameConverter.toDto(entity);  
        }).collect(Collectors.toList());
    }

    @Override
     public List<GameDto> findAllByAwayTeam(String teamName) {
        List<GameEntity> games = gameRepository.findAllByAway_TeamName(teamName);
        return games.stream().map((entity)->{
          return gameConverter.toDto(entity);  
        }).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        gameRepository.deleteById(id);
    }

}
