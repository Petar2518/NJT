/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.fon.silab.application.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.fon.silab.application.model.GameEntity;

/**
 *
 * @author gg
 */
@Repository
public interface GameRepository extends JpaRepository<GameEntity, Long>{
    List<GameEntity> findAllByHome_TeamName(String home);
    List<GameEntity> findAllByAway_TeamName(String away);
    
}
