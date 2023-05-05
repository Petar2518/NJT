/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.fon.silab.application.service;

import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;
import rs.fon.silab.application.dto.TeamDto;
import rs.fon.silab.application.exception.EntityDoesntExistException;
import rs.fon.silab.application.exception.EntityExistsException;

/**
 *
 * @author gg
 */
@Transactional
public interface TeamService {

    TeamDto save(TeamDto teamDto) throws EntityExistsException;

    public Optional<TeamDto> findById(Long id);

    List<TeamDto> findAll();

    List<TeamDto> findAllByCountry(String country);

    List<TeamDto> findAllByCity(String city);

    List<TeamDto> findByName(String name);

    TeamDto updateLocation(TeamDto teamDto, Long id) throws EntityDoesntExistException;

    void delete(Long id);

}
