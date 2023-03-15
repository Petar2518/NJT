/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.silab.application.service;

import java.util.List;
import java.util.Optional;
import rs.fon.silab.application.dto.GameDto;
import rs.fon.silab.application.exception.EntityExistsException;

/**
 *
 * @author gg
 */
public interface GameService {

    GameDto save(GameDto gameDto) throws EntityExistsException;

    List<GameDto> findAll();

    Optional<GameDto> findById(Long id);

    List<GameDto> findAllByHomeTeam(String teamName);

    List<GameDto> findAllByAwayTeam(String teamName);

    void delete(Long id);
}
