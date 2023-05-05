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
import rs.fon.silab.application.model.LeagueEntity;

/**
 *
 * @author gg
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface LeagueRepository extends JpaRepository<LeagueEntity, Long> {

    public List<LeagueEntity> findAllByLeagueNation(String nation);

    public List<LeagueEntity> findAllByLeagueDivision(String division);

    public List<LeagueEntity> findAllByLeagueName(String name);

}
