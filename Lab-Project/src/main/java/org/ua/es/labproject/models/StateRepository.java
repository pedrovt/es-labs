package org.ua.es.labproject.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * labproject - PublicAPI_Entry <br>
 * @author Pedro Teixeira pedro.teix@ua.pt
 * @author Paulo Vasconcelos paulobvasconcelos@gmail.com
 * @version 1.0 - February 22, 2020
 */
public interface StateRepository extends JpaRepository<State, Long> {

    List<State> findByid(long id);
    List<State> findByicao24(String icao24);
    List<State> findBycallsign(String callsign);


}