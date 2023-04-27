/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.fon.silab.application.service;

import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;
import rs.fon.silab.application.dto.LeagueDto;
import rs.fon.silab.application.exception.EntityDoesntExistException;
import rs.fon.silab.application.exception.EntityExistsException;

/**
 *
 * @author gg
 */
@Transactional
public interface LeagueService {
    
    LeagueDto save(LeagueDto leagueDto) throws EntityExistsException;
    
    List<LeagueDto> findAll();
    
    List<LeagueDto> findAllByNation(String nation);
    
    List<LeagueDto> findAllByDivision(String division);
    
    List<LeagueDto> findByName(String name);
    
    Optional<LeagueDto> findById(Long id);
    
    LeagueDto updateName(LeagueDto leagueDto, Long id) throws EntityDoesntExistException;
    
    void delete(Long id);
}
