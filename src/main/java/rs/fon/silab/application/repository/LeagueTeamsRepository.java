/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.silab.application.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import rs.fon.silab.application.model.LeagueTeamsEntity;

/**
 *
 * @author gg
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface LeagueTeamsRepository extends JpaRepository<LeagueTeamsEntity, LeagueTeamsEntity.ltId>{
    
    List<LeagueTeamsEntity> findAllByIdLeagueId(Long leagueId);

    List<LeagueTeamsEntity> findAllByIdTeamId(Long teamId);
}
