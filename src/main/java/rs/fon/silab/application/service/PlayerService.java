/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.fon.silab.application.service;

import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;
import rs.fon.silab.application.dto.PlayerDto;
import rs.fon.silab.application.exception.EntityDoesntExistException;
import rs.fon.silab.application.exception.EntityExistsException;

/**
 *
 * @author gg
 */
@Transactional
public interface PlayerService {

    public PlayerDto save(PlayerDto playerDto) throws EntityExistsException;

    public List<PlayerDto> findAllByTeam(String teamName);

    public List<PlayerDto> findByAgeLessThanEqual(int age);

    public Optional<PlayerDto> findById(Long id);

    public void delete(Long id);

    public List<PlayerDto> findByAgeGreaterThan(int age);
    
    public PlayerDto updateInfo(PlayerDto playerDto, long id) throws EntityDoesntExistException;
    
    public List<PlayerDto> findAll();

}
