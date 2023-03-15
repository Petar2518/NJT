/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.silab.application.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import rs.fon.silab.application.dto.GameGoalscorerDto;
import rs.fon.silab.application.exception.EntityExistsException;
import rs.fon.silab.application.service.GameGoalscorerService;

/**
 *
 * @author gg
 */
@RestController
public class GameGoalscorerRestController {
    
    @Autowired
    GameGoalscorerService ggService;
    
    @GetMapping("goalscorers/all")
    public List<GameGoalscorerDto> findAll(){
        return ggService.findAll();
    }
    
    @PostMapping("goalscorers/save")
    public ResponseEntity<Object> save(@Valid @RequestBody GameGoalscorerDto ggDto){
        try {
            return ResponseEntity.ok(ggService.save(ggDto));
        } catch (EntityExistsException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
    
}
