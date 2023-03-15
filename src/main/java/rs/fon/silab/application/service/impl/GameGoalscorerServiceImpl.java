/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.silab.application.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import rs.fon.silab.application.converter.GameGoalscorerConverter;
import rs.fon.silab.application.dto.GameGoalscorerDto;
import rs.fon.silab.application.exception.EntityExistsException;
import rs.fon.silab.application.model.GameGoalscorerEntity;
import rs.fon.silab.application.repository.GameGoalscorerRepository;
import rs.fon.silab.application.service.GameGoalscorerService;

/**
 *
 * @author gg
 */
@Service
public class GameGoalscorerServiceImpl implements GameGoalscorerService{
    
    private final GameGoalscorerRepository ggRepository;
    private final GameGoalscorerConverter ggConverter;

    public GameGoalscorerServiceImpl(GameGoalscorerRepository ggRepository, GameGoalscorerConverter ggConverter) {
        this.ggRepository = ggRepository;
        this.ggConverter = ggConverter;
    }
    
    

    @Override
    public GameGoalscorerDto save(GameGoalscorerDto ggDto) throws EntityExistsException {
        Optional<GameGoalscorerEntity> entity = ggRepository.findById(ggDto.getId());
        if (entity.isPresent()){
            throw new EntityExistsException(entity.get(),"Goalscorer already exsists");
        }
        return ggConverter.toDto(ggRepository.save(ggConverter.toEntity(ggDto)));
    }

    @Override
    public List<GameGoalscorerDto> findAll() {
        List<GameGoalscorerEntity> goalscorers = ggRepository.findAll();
        return goalscorers.stream().map((entity)->{
            return ggConverter.toDto(entity);
        }).collect(Collectors.toList());
    }
    
}
