/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.silab.application.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import rs.fon.silab.application.dto.TeamDto;
import rs.fon.silab.application.exception.EntityDoesntExistException;
import rs.fon.silab.application.exception.EntityExistsException;
import rs.fon.silab.application.service.TeamService;

/**
 *
 * @author gg
 */
@RestController
public class TeamRestController {

    @Autowired
    private TeamService teamService;

    @GetMapping("/teams")
    public List<TeamDto> allTeams() {
        return teamService.findAll();
    }
    @GetMapping("/teams/team/{name}")
    public ResponseEntity<Object> findByName(@PathVariable String name) {
        Optional<TeamDto> entity = teamService.findByName(name);
        if (entity.isPresent()) {
            return ResponseEntity.ok(entity.get());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid team");
    }

    @PostMapping(value = "/teams/team/{name}")
    public ResponseEntity<Object> save(@RequestBody TeamDto teamDto) {
        try {
            return ResponseEntity.ok(teamService.save(teamDto));
        } catch (EntityExistsException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
    @GetMapping("findbycity/{city}")
    public List<TeamDto> findTeamsByCity(@PathVariable String city) {
        return teamService.findAllByCity(city);
    }

    @GetMapping("findbycountry/{country}")
    public List<TeamDto> findTeamsByCountry(@PathVariable String country) {
        return teamService.findAllByCountry(country);
    }

    @DeleteMapping("/teams/delete/{name}")
    public void delete(@PathVariable String name) {
        teamService.delete(name);
    }
    @PutMapping("teams/team/{name}")
    public ResponseEntity<Object> update(@PathVariable String name, @RequestBody TeamDto teamDto){
        try {
            return ResponseEntity.ok(teamService.updateLocation(teamDto, name));
        } catch (EntityDoesntExistException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleError(MethodArgumentNotValidException ex) {
        Map<String, String> map = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            map.put(fieldName, errorMessage);
        });
        return map;
    }
}
