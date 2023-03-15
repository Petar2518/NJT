/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.fon.silab.application.service;

import java.util.List;
import java.util.Optional;
import rs.fon.silab.application.dto.PlayerDto;
import rs.fon.silab.application.exception.EntityExistsException;

/**
 *
 * @author gg
 */
public interface PlayerService {

    PlayerDto save(PlayerDto playerDto) throws EntityExistsException;

    List<PlayerDto> findAllByTeam(String teamName);

    List<PlayerDto> findByAgeLessThanEqual(int age);

    Optional<PlayerDto> findById(Long id);

    void delete(Long id);

    public List<PlayerDto> findByAgeGreaterThan(int age);
}
