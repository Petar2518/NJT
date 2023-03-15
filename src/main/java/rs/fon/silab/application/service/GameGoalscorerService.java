/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.fon.silab.application.service;

import java.util.List;
import rs.fon.silab.application.dto.GameGoalscorerDto;
import rs.fon.silab.application.exception.EntityExistsException;

/**
 *
 * @author gg
 */
public interface GameGoalscorerService {
    
    GameGoalscorerDto save(GameGoalscorerDto ggDto) throws EntityExistsException;
    
    List<GameGoalscorerDto> findAll();
}
