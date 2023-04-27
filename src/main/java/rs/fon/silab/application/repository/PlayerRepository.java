/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.fon.silab.application.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import rs.fon.silab.application.model.PlayerEntity;

/**
 *
 * @author gg
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface PlayerRepository extends JpaRepository<PlayerEntity, Long> {

    List<PlayerEntity> findAllByTeam_TeamName(String team);

    List<PlayerEntity> findByAgeLessThanEqual(int age);

    List<PlayerEntity> findByAgeGreaterThan(int age);
}
