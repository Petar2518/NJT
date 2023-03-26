/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.fon.silab.application.service;

import java.util.List;
import java.util.Optional;
import rs.fon.silab.application.dto.GameGoalscorerDto;
import rs.fon.silab.application.exception.EntityDoesntExistException;
import rs.fon.silab.application.exception.EntityExistsException;
import rs.fon.silab.application.exception.PlayerNotInTeamException;
import rs.fon.silab.application.exception.PlayerScoredMoreThanTeamException;

/**
 *
 * @author gg
 */
public interface GameGoalscorerService {
    
    List<GameGoalscorerDto> findAll();
    
    GameGoalscorerDto save(GameGoalscorerDto ggDto) throws EntityExistsException, PlayerNotInTeamException, PlayerScoredMoreThanTeamException;
    
    List<GameGoalscorerDto> findAllGoalscorers(Long gameId);
    
    List<GameGoalscorerDto> findAllGamesScored(Long playerId);
    
    Optional<GameGoalscorerDto> findByGamePlayer(Long gameId, Long playerId);
    
    void delete(Long gameId, Long playerId);
    
    GameGoalscorerDto update(GameGoalscorerDto ggDto, Long gameId, long playerId) throws EntityDoesntExistException, PlayerScoredMoreThanTeamException;
    
    
}
