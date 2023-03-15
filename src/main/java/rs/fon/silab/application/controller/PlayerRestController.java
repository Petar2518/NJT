/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.silab.application.controller;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rs.fon.silab.application.dto.PlayerDto;
import rs.fon.silab.application.exception.EntityExistsException;
import rs.fon.silab.application.service.PlayerService;

/**
 *
 * @author gg
 */
@RestController
public class PlayerRestController {

    @Autowired
    private PlayerService playerService;

    @GetMapping("{team}/players")
    public List<PlayerDto> findPlayersByTeam(@PathVariable String team) {
        return playerService.findAllByTeam(team);
    }

    @PostMapping("players/save")
    public ResponseEntity<Object> save(@Valid @RequestBody PlayerDto playerDto) {
        {
            try {
                return ResponseEntity.ok(playerService.save(playerDto));
            } catch (EntityExistsException ex) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
            }
        }
    }

    @GetMapping("players/youngerthan/{age}")
    public List<PlayerDto> findPlayersYoungerThan(@PathVariable int age) {
        return playerService.findByAgeLessThanEqual(age);
    }

    @GetMapping("players/olderthan/{age}")
    public List<PlayerDto> findPlayersOlderThan(@PathVariable int age) {
        return playerService.findByAgeGreaterThan(age);
    }

    @GetMapping("players/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        Optional<PlayerDto> entity = playerService.findById(id);
        if (entity.isPresent()) {
            return ResponseEntity.ok(entity.get());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid player");
    }

    @RequestMapping(value = "players/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public void delete(@PathVariable Long id) {
        playerService.delete(id);
    }
}
