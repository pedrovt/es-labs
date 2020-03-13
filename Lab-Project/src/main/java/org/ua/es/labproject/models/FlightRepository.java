package org.ua.es.labproject.models;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * labproject - FlightsRepository <br>
 *
 * @author Paulo Vasconcelos paulobvasconcelos@gmail.com
 * @author Pedro Teixeira pedro.teix@ua.pt
 * @version 2.0 - March 11, 2020
 */
public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findByFlightID(Long flightID);
    List<Flight> findByicao24(String icao24);
    List<Flight> findBycallsign(String callsign);


}