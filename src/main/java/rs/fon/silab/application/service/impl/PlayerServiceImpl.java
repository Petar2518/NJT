/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.silab.application.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import rs.fon.silab.application.converter.PlayerConverter;
import rs.fon.silab.application.dto.PlayerDto;
import rs.fon.silab.application.exception.EntityExistsException;
import rs.fon.silab.application.model.PlayerEntity;
import rs.fon.silab.application.repository.PlayerRepository;
import rs.fon.silab.application.service.PlayerService;

/**
 *
 * @author gg
 */
@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerConverter playerConverter;
    private final PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerConverter playerConverter, PlayerRepository playerRepository) {
        this.playerConverter = playerConverter;
        this.playerRepository = playerRepository;
    }

    @Override
    public List<PlayerDto> findAllByTeam(String teamName) {
        List<PlayerEntity> players = playerRepository.findAllByTeam_TeamName(teamName);
        return players.stream().map((entity) -> {
            return playerConverter.toDto(entity);
        }).collect(Collectors.toList());
    }

    @Override
    public PlayerDto save(PlayerDto playerDto) throws EntityExistsException {
        Optional<PlayerEntity> entity = playerRepository.findById(playerDto.getPlayerId());
        if (entity.isPresent()) {
            throw new EntityExistsException(entity.get(), "Player already exists");
        }
        return playerConverter.toDto(playerRepository.save(playerConverter.toEntity(playerDto)));
    }

    @Override
    public List<PlayerDto> findByAgeLessThanEqual(int age) {
        List<PlayerEntity> players = playerRepository.findByAgeLessThanEqual(age);
        return players.stream().map((entity) -> {
            return playerConverter.toDto(entity);
        }).collect(Collectors.toList());
    }

    @Override
    public Optional<PlayerDto> findById(Long id) {
        Optional<PlayerEntity> entity = playerRepository.findById(id);
        if (entity.isPresent()) {
            return Optional.of(playerConverter.toDto(entity.get()));
        }
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {
        playerRepository.deleteById(id);
    }

    @Override
    public List<PlayerDto> findByAgeGreaterThan(int age) {
        List<PlayerEntity> players = playerRepository.findByAgeGreaterThan(age);
        return players.stream().map((entity)->{
            return playerConverter.toDto(entity);
        }).collect(Collectors.toList());
    }

}
