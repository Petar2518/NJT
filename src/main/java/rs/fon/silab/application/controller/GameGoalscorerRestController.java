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
import rs.fon.silab.application.dto.GameGoalscorerDto;
import rs.fon.silab.application.exception.EntityDoesntExistException;
import rs.fon.silab.application.exception.EntityExistsException;
import rs.fon.silab.application.exception.PlayerNotInTeamException;
import rs.fon.silab.application.exception.PlayerScoredMoreThanTeamException;
import rs.fon.silab.application.service.GameGoalscorerService;

/**
 *
 * @author gg
 */
@RestController
public class GameGoalscorerRestController {

    @Autowired
    GameGoalscorerService ggService;

    @GetMapping("goalscorers")
    public List<GameGoalscorerDto> findAll(){
        return ggService.findAll();
    }
    @GetMapping("gamegoalscorers/{game}/")
    public List<GameGoalscorerDto> findAllGoalscorers(@PathVariable Long game) {
        return ggService.findAllGoalscorers(game);
    }
    @GetMapping("playergoals/{player}/")
    public List<GameGoalscorerDto> findAllGamesScored(@PathVariable Long player){
        return ggService.findAllGamesScored(player);
    }

    @GetMapping("goalscorers/{game}/{player}")
    public ResponseEntity<Object> findById(@PathVariable Long game, @PathVariable Long player){
        Optional<GameGoalscorerDto> entity = ggService.findByGamePlayer(game,player);
        if(entity.isPresent()){
            return ResponseEntity.ok(entity.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid league");
    }

    @PostMapping("goalscorers/goalscorer/add")
    public ResponseEntity<Object> save(@Valid @RequestBody GameGoalscorerDto ggDto) {
        try {
            return ResponseEntity.ok(ggService.save(ggDto));
        } catch (EntityExistsException | PlayerNotInTeamException | PlayerScoredMoreThanTeamException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
    
    @DeleteMapping(value = "goalscorers/delete/{game}/{player}")
    public void delete(@PathVariable Long game,@PathVariable Long player) {
        ggService.delete(game, player);

    }
    
    @PutMapping(value = "goalscorers/goalscorer/{game}/{player}")
    public ResponseEntity<Object> update(@PathVariable Long game,@PathVariable Long player, @RequestBody GameGoalscorerDto goalscorerDto){
        try {
            return ResponseEntity.ok(ggService.update(goalscorerDto, game, player));
        } catch (EntityDoesntExistException | PlayerScoredMoreThanTeamException ex) {
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
