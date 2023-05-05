/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.silab.application.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.fon.silab.application.converter.GameConverter;
import rs.fon.silab.application.dto.GameDto;
import rs.fon.silab.application.dto.LeagueDto;
import rs.fon.silab.application.dto.LeagueTeamsDto;
import rs.fon.silab.application.dto.TeamDto;
import rs.fon.silab.application.exception.EntityDoesntExistException;
import rs.fon.silab.application.exception.EntityExistsException;
import rs.fon.silab.application.exception.NoMutualLeaguesException;
import rs.fon.silab.application.exception.SameTeamsException;
import rs.fon.silab.application.model.GameEntity;
import rs.fon.silab.application.repository.GameRepository;
import rs.fon.silab.application.service.GameService;
import rs.fon.silab.application.service.LeagueTeamsService;

/**
 *
 * @author gg
 */
@Service

public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final GameConverter gameConverter;
    private final LeagueTeamsService ltService;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository, GameConverter gameConverter, LeagueTeamsService ltService) {
        this.gameRepository = gameRepository;
        this.gameConverter = gameConverter;
        this.ltService = ltService;
    }

    @Override
    public GameDto save(GameDto gameDto) throws EntityExistsException, SameTeamsException, NoMutualLeaguesException {
        LeagueDto league = gameDto.getLeague();
        TeamDto homeTeam = gameDto.getHomeTeam();
        TeamDto awayTeam = gameDto.getAwayTeam();
        List<TeamDto> teams = ltService.findAllTeams(gameDto.getLeague().getLeagueId()).stream().map((entity) -> {
            return entity.getTeam();
        }).collect(Collectors.toList());
        if (!(teams.contains(homeTeam) && teams.contains(awayTeam))) {
            throw new NoMutualLeaguesException(gameDto, "Teams arent participating in given league");
        }

        int homeGoals = gameDto.getHomeTeamGoals();
        int awayGoals = gameDto.getAwayTeamGoals();
        try {
            if (homeGoals > awayGoals) {
                LeagueTeamsDto points = ltService.findByLeagueTeam(league.getLeagueId(), homeTeam.getTeamId()).get();
                points.setPoints(points.getPoints() + 3);
                ltService.update(points, league.getLeagueId(), homeTeam.getTeamId());
            } else if (homeGoals < awayGoals) {
                LeagueTeamsDto points = ltService.findByLeagueTeam(league.getLeagueId(), awayTeam.getTeamId()).get();
                points.setPoints(points.getPoints() + 3);
                ltService.update(points, league.getLeagueId(), awayTeam.getTeamId());
            } else {
                LeagueTeamsDto pointsAway = ltService.findByLeagueTeam(league.getLeagueId(), awayTeam.getTeamId()).get();
                LeagueTeamsDto pointsHome = ltService.findByLeagueTeam(league.getLeagueId(), homeTeam.getTeamId()).get();
                pointsAway.setPoints(pointsAway.getPoints() + 1);
                pointsHome.setPoints(pointsHome.getPoints() + 1);
                ltService.update(pointsAway, league.getLeagueId(), homeTeam.getTeamId());
                ltService.update(pointsHome, league.getLeagueId(), awayTeam.getTeamId());
            }
        } catch (EntityDoesntExistException ex) {
            Logger.getLogger(GameServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
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
        return games.stream().map((entity) -> {
            return gameConverter.toDto(entity);
        }).collect(Collectors.toList());
    }

    @Override
    public List<GameDto> findAllByAwayTeam(String teamName) {
        List<GameEntity> games = gameRepository.findAllByAway_TeamName(teamName);
        return games.stream().map((entity) -> {
            return gameConverter.toDto(entity);
        }).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) throws EntityDoesntExistException {
        Optional<GameEntity> game = gameRepository.findById(id);
        GameDto gameD = gameConverter.toDto(game.get());
        LeagueDto league = gameD.getLeague();
        TeamDto homeTeam = gameD.getHomeTeam();
        TeamDto awayTeam = gameD.getAwayTeam();
        int htGoals = gameD.getHomeTeamGoals();
        int atGoals = gameD.getAwayTeamGoals();
        if (htGoals > atGoals) {
            LeagueTeamsDto pointsHome = ltService.findByLeagueTeam(league.getLeagueId(), homeTeam.getTeamId()).get();
            pointsHome.setPoints(pointsHome.getPoints() - 3);
            ltService.update(pointsHome, league.getLeagueId(), homeTeam.getTeamId());
        } else if (htGoals == atGoals) {
            LeagueTeamsDto pointsHome = ltService.findByLeagueTeam(league.getLeagueId(), homeTeam.getTeamId()).get();
            pointsHome.setPoints(pointsHome.getPoints() - 1);
            ltService.update(pointsHome, league.getLeagueId(), homeTeam.getTeamId());
            LeagueTeamsDto pointsAway = ltService.findByLeagueTeam(league.getLeagueId(), awayTeam.getTeamId()).get();
            pointsAway.setPoints(pointsAway.getPoints() - 1);
            ltService.update(pointsAway, league.getLeagueId(), awayTeam.getTeamId());
        } else {
            LeagueTeamsDto pointsAway = ltService.findByLeagueTeam(league.getLeagueId(), awayTeam.getTeamId()).get();
            pointsAway.setPoints(pointsAway.getPoints() - 3);
            ltService.update(pointsAway, league.getLeagueId(), awayTeam.getTeamId());
        }
        gameRepository.deleteById(id);
    }

    @Override
    public GameDto updateResult(GameDto gameDto, Long id) throws EntityDoesntExistException {
        Optional<GameEntity> game = gameRepository.findById(id);
        if (game.isEmpty()) {
            throw new EntityDoesntExistException(gameDto, "Entity with given id doesnt exist.");
        }
        GameDto gameD = gameConverter.toDto(game.get());
        LeagueDto league = gameDto.getLeague();
        TeamDto homeTeam = gameDto.getHomeTeam();
        TeamDto awayTeam = gameDto.getAwayTeam();
        int htGoals = gameD.getHomeTeamGoals();
        int htNew = gameDto.getHomeTeamGoals();
        int atGoals = gameD.getAwayTeamGoals();
        int atNew = gameDto.getAwayTeamGoals();
        if (htGoals > atGoals) {
            if (htNew > atNew) {
            } else if (htNew == atNew) {
                LeagueTeamsDto pointsHome = ltService.findByLeagueTeam(league.getLeagueId(), homeTeam.getTeamId()).get();
                pointsHome.setPoints(pointsHome.getPoints() - 2);
                ltService.update(pointsHome, league.getLeagueId(), homeTeam.getTeamId());
                LeagueTeamsDto pointsAway = ltService.findByLeagueTeam(league.getLeagueId(), awayTeam.getTeamId()).get();
                pointsAway.setPoints(pointsAway.getPoints() + 1);
                ltService.update(pointsAway, league.getLeagueId(), awayTeam.getTeamId());
            } else {
                LeagueTeamsDto pointsHome = ltService.findByLeagueTeam(league.getLeagueId(), homeTeam.getTeamId()).get();
                pointsHome.setPoints(pointsHome.getPoints() - 3);
                ltService.update(pointsHome, league.getLeagueId(), homeTeam.getTeamId());
                LeagueTeamsDto pointsAway = ltService.findByLeagueTeam(league.getLeagueId(), awayTeam.getTeamId()).get();
                pointsAway.setPoints(pointsAway.getPoints() + 3);
                ltService.update(pointsAway, league.getLeagueId(), awayTeam.getTeamId());
            }
        } else if (htGoals == atGoals) {
            if (htNew == atNew) {
            } else if (htNew > atNew) {
                LeagueTeamsDto pointsHome = ltService.findByLeagueTeam(league.getLeagueId(), homeTeam.getTeamId()).get();
                pointsHome.setPoints(pointsHome.getPoints() + 2);
                ltService.update(pointsHome, league.getLeagueId(), homeTeam.getTeamId());
                LeagueTeamsDto pointsAway = ltService.findByLeagueTeam(league.getLeagueId(), awayTeam.getTeamId()).get();
                pointsAway.setPoints(pointsAway.getPoints() - 1);
                ltService.update(pointsAway, league.getLeagueId(), awayTeam.getTeamId());
            } else {
                LeagueTeamsDto pointsHome = ltService.findByLeagueTeam(league.getLeagueId(), homeTeam.getTeamId()).get();
                pointsHome.setPoints(pointsHome.getPoints() - 1);
                ltService.update(pointsHome, league.getLeagueId(), homeTeam.getTeamId());
                LeagueTeamsDto pointsAway = ltService.findByLeagueTeam(league.getLeagueId(), awayTeam.getTeamId()).get();
                pointsAway.setPoints(pointsAway.getPoints() + 2);
                ltService.update(pointsAway, league.getLeagueId(), awayTeam.getTeamId());
            }
        } else {
            if (htNew < atNew) {
            } else if (htNew == atNew) {
                LeagueTeamsDto pointsHome = ltService.findByLeagueTeam(league.getLeagueId(), homeTeam.getTeamId()).get();
                pointsHome.setPoints(pointsHome.getPoints() + 2);
                ltService.update(pointsHome, league.getLeagueId(), homeTeam.getTeamId());
                LeagueTeamsDto pointsAway = ltService.findByLeagueTeam(league.getLeagueId(), awayTeam.getTeamId()).get();
                pointsAway.setPoints(pointsAway.getPoints() - 1);
                ltService.update(pointsAway, league.getLeagueId(), awayTeam.getTeamId());
            } else {
                LeagueTeamsDto pointsHome = ltService.findByLeagueTeam(league.getLeagueId(), homeTeam.getTeamId()).get();
                pointsHome.setPoints(pointsHome.getPoints() + 3);
                ltService.update(pointsHome, league.getLeagueId(), homeTeam.getTeamId());
                LeagueTeamsDto pointsAway = ltService.findByLeagueTeam(league.getLeagueId(), awayTeam.getTeamId()).get();
                pointsAway.setPoints(pointsAway.getPoints() - 3);
                ltService.update(pointsAway, league.getLeagueId(), awayTeam.getTeamId());
            }
        }
        gameD.setHomeTeamGoals(gameDto.getHomeTeamGoals());
        gameD.setAwayTeamGoals(gameDto.getAwayTeamGoals());
        return gameConverter.toDto(gameRepository.save(gameConverter.toEntity(gameD)));
    }

}
