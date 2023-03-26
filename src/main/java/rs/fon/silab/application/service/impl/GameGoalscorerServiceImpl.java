/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.silab.application.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import rs.fon.silab.application.converter.GameGoalscorerConverter;
import rs.fon.silab.application.dto.GameDto;
import rs.fon.silab.application.dto.GameGoalscorerDto;
import rs.fon.silab.application.dto.PlayerDto;
import rs.fon.silab.application.dto.TeamDto;
import rs.fon.silab.application.exception.EntityDoesntExistException;
import rs.fon.silab.application.exception.EntityExistsException;
import rs.fon.silab.application.exception.PlayerNotInTeamException;
import rs.fon.silab.application.exception.PlayerScoredMoreThanTeamException;
import rs.fon.silab.application.model.GameGoalscorerEntity;
import rs.fon.silab.application.model.GameGoalscorerEntity.ggId;
import rs.fon.silab.application.repository.GameGoalscorerRepository;
import rs.fon.silab.application.service.GameGoalscorerService;
import rs.fon.silab.application.service.GameService;
import rs.fon.silab.application.service.PlayerService;
import rs.fon.silab.application.service.TeamService;

/**
 *
 * @author gg
 */
@Service
public class GameGoalscorerServiceImpl implements GameGoalscorerService {

    private final GameGoalscorerRepository ggRepository;
    private final GameGoalscorerConverter ggConverter;
    private final PlayerService playerService;
    private final GameService gameService;

    public GameGoalscorerServiceImpl(GameGoalscorerRepository ggRepository, GameGoalscorerConverter ggConverter, PlayerService playerService, GameService gameService) {
        this.ggRepository = ggRepository;
        this.ggConverter = ggConverter;
        this.playerService = playerService;
        this.gameService = gameService;
    }

    
    @Override
    public GameGoalscorerDto save(GameGoalscorerDto ggDto) throws EntityExistsException, PlayerNotInTeamException, PlayerScoredMoreThanTeamException {
        GameGoalscorerEntity.ggId ggid = new GameGoalscorerEntity.ggId(ggDto.getGame().getGameId(), ggDto.getPlayer().getPlayerId());
        Optional<GameGoalscorerEntity> entity = ggRepository.findById(ggid);
        if (entity.isPresent()) {
            throw new EntityExistsException(entity, "Goalscorer for that game already exists");
        }
        Long playerId = ggDto.getPlayer().getPlayerId();
        Long gameId = ggDto.getGame().getGameId();
        PlayerDto player = playerService.findById(playerId).get();
        GameDto game = gameService.findById(gameId).get();
        TeamDto team = player.getTeam();
        List<TeamDto> teams = new ArrayList<>();
        teams.add(game.getAwayTeam());
        teams.add(game.getHomeTeam());
        TeamDto homeTeam=game.getHomeTeam();
        if (!(teams.contains(team))) {
            throw new PlayerNotInTeamException(ggDto.getPlayer(), "Player doesnt play for teams in this game.");
        }
        int remainingGoalsHome = game.getHomeTeamGoals();
        int remainingGoalsAway = game.getAwayTeamGoals();
        for (GameGoalscorerDto gs : findAllGoalscorers(gameId)) {
            if(playerService.findById(gs.getPlayer().getPlayerId()).get().getTeam().equals(homeTeam)){
                remainingGoalsHome-=gs.getGoals();
            }else remainingGoalsAway-=gs.getGoals();
        }   
        if (team.equals(game.getHomeTeam())) {
            if (ggDto.getGoals() > remainingGoalsHome) {
                throw new PlayerScoredMoreThanTeamException(ggDto, "Team has scored " + game.getHomeTeamGoals() + "goals and " + (game.getHomeTeamGoals()-remainingGoalsHome) + "of goals have already been assigned to other players.");
            }
        }
        if (team.equals(game.getAwayTeam())) {
            if (ggDto.getGoals() > remainingGoalsAway) {
                throw new PlayerScoredMoreThanTeamException(ggDto, "Team has scored " + game.getAwayTeamGoals() + "goals and " + (game.getAwayTeamGoals()-remainingGoalsAway) + "of goals have already been assigned to other players.");
            }
        }

        return ggConverter.toDto(ggRepository.save(ggConverter.toEntity(ggDto)));
    }

    @Override
    public List<GameGoalscorerDto> findAllGoalscorers(Long gameId) {
        List<GameGoalscorerEntity> goalscorers = ggRepository.findAllByIdGameId(gameId);
        return goalscorers.stream().map((entity) -> {
            return ggConverter.toDto(entity);
        }).collect(Collectors.toList());
    }

    @Override
    public List<GameGoalscorerDto> findAllGamesScored(Long playerId) {
        List<GameGoalscorerEntity> games = ggRepository.findAllByIdPlayerId(playerId);
        return games.stream().map((entity) -> {
            return ggConverter.toDto(entity);
        }).collect(Collectors.toList());
    }

    @Override
    public void delete(Long gameId, Long playerId) {
        GameGoalscorerEntity.ggId id = new ggId(gameId, playerId);
        ggRepository.deleteById(id);
    }

    @Override
    public GameGoalscorerDto update(GameGoalscorerDto ggDto, Long gameId, long playerId)throws EntityDoesntExistException, PlayerScoredMoreThanTeamException{
        GameGoalscorerEntity.ggId id = new GameGoalscorerEntity.ggId(gameId, playerId);
        Optional<GameGoalscorerEntity> goalscorer = ggRepository.findById(id);
        if (goalscorer.isEmpty()){
            throw new EntityDoesntExistException(ggDto,"Entity with given id doesnt exist.");
        }
        GameGoalscorerDto goalscorerD=ggConverter.toDto(goalscorer.get());
        PlayerDto player = playerService.findById(playerId).get();
        TeamDto team = player.getTeam();
        GameDto game = gameService.findById(gameId).get();
        int remainingGoalsHome = game.getHomeTeamGoals();
        int remainingGoalsAway = game.getAwayTeamGoals();
        for (GameGoalscorerDto gs : findAllGoalscorers(gameId)) {
            if(playerService.findById(gs.getPlayer().getPlayerId()).get().getTeam().equals(game.getHomeTeam())){
                
                remainingGoalsHome-=gs.getGoals();
            }else remainingGoalsAway-=gs.getGoals();
        }
        if (team.equals(game.getHomeTeam())) {
            remainingGoalsHome+=goalscorer.get().getGoals();
            if (ggDto.getGoals() > remainingGoalsHome) {
                throw new PlayerScoredMoreThanTeamException(ggDto, "Team has scored " + game.getHomeTeamGoals() + "goals and " + (game.getHomeTeamGoals()-remainingGoalsHome) + "of goals have already been assigned to other players.");
            }
        }
        if (team.equals(game.getAwayTeam())) {
            remainingGoalsAway +=goalscorer.get().getGoals();
            if (ggDto.getGoals() > remainingGoalsAway ) {
                throw new PlayerScoredMoreThanTeamException(ggDto, "Team has scored " + game.getAwayTeamGoals() + "goals and " + (game.getAwayTeamGoals()-remainingGoalsAway) + "of goals have already been assigned to other players.");
            }
        }
        goalscorerD.setGoals(ggDto.getGoals());
        return ggConverter.toDto(ggRepository.save(ggConverter.toEntity(goalscorerD)));
    }

    @Override
    public List<GameGoalscorerDto> findAll() {
        List<GameGoalscorerEntity> games = ggRepository.findAll();
        return games.stream().map((entity) -> {
            return ggConverter.toDto(entity);
        }).collect(Collectors.toList());
    }

    @Override
    public Optional<GameGoalscorerDto> findByGamePlayer(Long gameId, Long playerId) {
        GameGoalscorerEntity.ggId id = new GameGoalscorerEntity.ggId(gameId, playerId);
         Optional<GameGoalscorerEntity> entity = ggRepository.findById(id);
        if (entity.isPresent()){
            return Optional.of(ggConverter.toDto(entity.get()));
        }
        return Optional.empty();
    }
    
    
    
}
