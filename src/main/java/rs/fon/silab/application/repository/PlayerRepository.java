/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.fon.silab.application.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.fon.silab.application.model.PlayerEntity;

/**
 *
 * @author gg
 */
@Repository
public interface PlayerRepository extends JpaRepository<PlayerEntity, Long> {

    List<PlayerEntity> findAllByTeam_TeamName(String team);

    List<PlayerEntity> findByAgeLessThanEqual(int age);

    List<PlayerEntity> findByAgeGreaterThan(int age);
}
