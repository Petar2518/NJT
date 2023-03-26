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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.fon.silab.application.dto.PlayerDto;
import rs.fon.silab.application.exception.EntityDoesntExistException;
import rs.fon.silab.application.exception.EntityExistsException;

import rs.fon.silab.application.service.PlayerService;

/**
 *
 * @author gg
 */

@RestController
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping
public class PlayerRestController {

    @Autowired
    private PlayerService playerService;

    @GetMapping("{team}/players")
    public List<PlayerDto> findPlayersByTeam(@PathVariable String team) {
        return playerService.findAllByTeam(team);
    }
    @GetMapping("youngerthan/{age}")
    public List<PlayerDto> findPlayersYoungerThan(@PathVariable int age) {
        return playerService.findByAgeLessThanEqual(age);
    }
    @GetMapping("olderthan/{age}")
    public List<PlayerDto> findPlayersOlderThan(@PathVariable int age) {
        return playerService.findByAgeGreaterThan(age);
    }
     @GetMapping("/players")
    public List<PlayerDto> findAll(){
        return playerService.findAll();
    }
    @GetMapping("/players/player/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        Optional<PlayerDto> entity = playerService.findById(id);
        if (entity.isPresent()) {
            return ResponseEntity.ok(entity.get());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid player");
    }

    @DeleteMapping("/playersdelete/{id}")
    public void delete(@PathVariable Long id) {
        playerService.delete(id);
    }
    @PutMapping("/players/player/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody PlayerDto playerDto){
        try {
            return ResponseEntity.ok(playerService.updateInfo(playerDto, id));
        } catch (EntityDoesntExistException ex) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
    @PostMapping("/players/player/add")
    public ResponseEntity<Object> save(@RequestBody PlayerDto playerDto) {
        {
                System.out.println(playerDto.getPlayerId());
                System.out.println(playerDto.getName());
            try {
                
                return ResponseEntity.ok(playerService.save(playerDto));
            } catch (EntityExistsException ex) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
            }
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
