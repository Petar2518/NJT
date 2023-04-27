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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.fon.silab.application.dto.GameDto;
import rs.fon.silab.application.exception.EntityDoesntExistException;
import rs.fon.silab.application.exception.EntityExistsException;
import rs.fon.silab.application.exception.SameTeamsException;
import rs.fon.silab.application.service.GameService;

/**
 *
 * @author gg
 */
@RestController
public class GameRestController {

    @Autowired
    private GameService gameService;


    @GetMapping("/games")
    public List<GameDto> allGames() {
        return gameService.findAll();
    }

    @GetMapping("/games/game/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        Optional<GameDto> entity = gameService.findById(id);
        if (entity.isPresent()) {
            return ResponseEntity.ok(entity.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid game");
    }

    @GetMapping("homegames/{teamName}/")
    public List<GameDto> findByHomeTeam(@PathVariable String teamName) {
        return gameService.findAllByHomeTeam(teamName);
    }

    @GetMapping("awaygames/{teamName}/")
    public List<GameDto> findByAwayTeam(@PathVariable String teamName) {
        return gameService.findAllByAwayTeam(teamName);
    }

    @GetMapping("/allgames/{teamName}")
    public List<GameDto> findAllGames(@PathVariable String teamName) {
        List<GameDto> games = gameService.findAllByHomeTeam(teamName);
        games.addAll(gameService.findAllByAwayTeam(teamName));
        return games;

    }

    @PostMapping("/games/game/add")
    public ResponseEntity<Object> save(@RequestBody GameDto gameDto) {
        try {
            return ResponseEntity.ok(gameService.save(gameDto));
        } catch (EntityExistsException | SameTeamsException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @DeleteMapping("/games/delete/{id}")
    public void delete(@PathVariable Long id) {
        gameService.delete(id);

    }
    @PutMapping("/games/game/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody GameDto gameDto ){
        try {
            return ResponseEntity.ok(gameService.updateResult(gameDto,id));
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
