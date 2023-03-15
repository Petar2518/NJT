/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.fon.silab.application.service;

import java.util.List;
import java.util.Optional;
import rs.fon.silab.application.dto.TeamDto;
import rs.fon.silab.application.exception.EntityExistsException;

/**
 *
 * @author gg
 */
public interface TeamService {

    TeamDto save(TeamDto teamDto) throws EntityExistsException;

    List<TeamDto> findAll();

    List<TeamDto> findAllByCountry(String country);

    List<TeamDto> findAllByCity(String city);

    Optional<TeamDto> findByName(String name);

    void delete(String name);

}
