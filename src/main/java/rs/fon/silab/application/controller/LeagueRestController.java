/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.silab.application.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
import rs.fon.silab.application.dto.LeagueDto;
import rs.fon.silab.application.exception.EntityDoesntExistException;
import rs.fon.silab.application.exception.EntityExistsException;
import rs.fon.silab.application.service.LeagueService;

/**
 *
 * @author gg
 */
@RestController
public class LeagueRestController {
    
    @Autowired
    private LeagueService leagueService;
    
    
    @GetMapping("/leagues/all")
    public List<LeagueDto> findAll(){
        return leagueService.findAll();
    }
    
    @GetMapping("leagues/nation/{nation}")
    public List<LeagueDto> findByNation(@PathVariable String nation){
        return leagueService.findAllByNation(nation);
    }
    
    @GetMapping("leagues/division/{division}")
    public List<LeagueDto> findByDivision(@PathVariable String division){
        return leagueService.findAllByDivision(division);
    }
    
    @GetMapping("leagues/name/{name}")
    public List<LeagueDto> findByName(@PathVariable String name){
        return leagueService.findByName(name);
    }
    @GetMapping("leagues/get/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id){
        Optional<LeagueDto> entity = leagueService.findById(id);
        if(entity.isPresent()){
            return ResponseEntity.ok(entity.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid league");
    }
    @PostMapping("leagues/save")
    public ResponseEntity<Object> save(@Valid @RequestBody LeagueDto leagueDto){
        try {
            return ResponseEntity.ok(leagueService.save(leagueDto));
        } catch (EntityExistsException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
    @RequestMapping(value="leagues/delete/{id}",method ={RequestMethod.DELETE, RequestMethod.GET})
    public void delete(Long id){
        leagueService.delete(id);
    }
    @RequestMapping(value = "leagues/update/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody LeagueDto leagueDto){
        try {
            return ResponseEntity.ok(leagueService.updateName(leagueDto, id));
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
