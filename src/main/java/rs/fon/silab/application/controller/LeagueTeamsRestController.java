/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.silab.application.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rs.fon.silab.application.dto.LeagueTeamsDto;
import rs.fon.silab.application.exception.EntityDoesntExistException;
import rs.fon.silab.application.exception.EntityExistsException;
import rs.fon.silab.application.service.LeagueTeamsService;

/**
 *
 * @author gg
 */
@RestController
public class LeagueTeamsRestController {
    
    @Autowired
    LeagueTeamsService ltService;
    
    @GetMapping("league/{leagueId}/teams")
    public List<LeagueTeamsDto> findAllTeams(@PathVariable Long leagueId){
        return ltService.findAllTeams(leagueId);
    }
    @GetMapping("team/{teamName}/leagues")
    public List<LeagueTeamsDto> findAllLeagues(@PathVariable String teamName){
        return ltService.findAllLeagues(teamName);
    }
    @PostMapping("league/team/save")
    public ResponseEntity<Object> save(@Valid @RequestBody LeagueTeamsDto ltDto){
        try {
            return ResponseEntity.ok(ltService.save(ltDto));
        } catch (EntityExistsException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
    
    @RequestMapping(value = "delete/{league}/{team}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public void delete(@PathVariable Long league, @PathVariable String team){
        ltService.delete(league, team);
    }
    
    @RequestMapping(value = "/update/{league}/{team}", method = {RequestMethod.GET, RequestMethod.PUT})
    public ResponseEntity<Object> update(@PathVariable Long league, @PathVariable String team,@Valid @RequestBody LeagueTeamsDto ltDto){
        try {
            return ResponseEntity.ok(ltService.update(ltDto, league, team));
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
