package org.ua.es.labproject.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.ua.es.labproject.models.APIResult;
import org.ua.es.labproject.models.Flight;

import java.util.Date;
import java.util.List;

/**
 * labproject - UpdateFlights <br>
 * <b>Not used</b> - issue with autowired prevented this code from being decoupled from FlightController <br>
 * As of March 14, no solution was found <br>
 *
 * @author Paulo Vasconcelos paulobvasconcelos@gmail.com
 * @author Pedro Teixeira pedro.teix@ua.pt
 * @version 1.0 - March 13, 2020
 */
@Service
public class UpdateFlights {

    /* Constants */
    private static final Logger log = LoggerFactory.getLogger(UpdateFlights.class);

    /* Instance Fields */
    @Autowired private static RestTemplate restTemplate;
    @Autowired private static FlightRepository repository;
    @Autowired private static List<Flight> cache;
    @Autowired public  static Date dbDate = new Date();

    public static void updateFlights() {
        log.info("Updating state cache (repository/database) with OpenSky API Info");

        try {
            /* Get states from API */
            List<Flight> resultsFromAPI = UpdateFlights.getFlightsFromAPI();
            resultsFromAPI = resultsFromAPI.subList(0, resultsFromAPI.size()/3);        // slide for performance

            /* Remove old states to avoid database capacity issues */
            for (Flight flight : resultsFromAPI) {
                List<Flight> statesToRemove = repository.findBycallsign(flight.getCallsign());
                repository.deleteAll(statesToRemove);
                repository.saveAndFlush(flight);
            }

            /* Update database update time */
            dbDate = new Date();

            log.info("Updated state cache (repository/database) with OpenSky API Info");

        } catch (Exception e) {
            log.error("ERROR! Error updating state cache (repository/database) with OpenSky API");
            log.error("API not available (" + e.toString() + ")");
            e.printStackTrace();
            log.warn("No changes to cache");
        }

        cache = repository.findAll();
        cache.sort((o1, o2) -> (int) (o1.getFlightID() - o2.getFlightID()));        // order cache
    }


    private static List<Flight> getFlightsFromAPI() {
        log.info("Obtaining list of states from OpenSky API");

        /* Build URL */
        String url = "https://opensky-network.org/api/states/all?lamin=45.8389&lomin=5.0&lamax=47.8229&lomax=11.5226";
        UriComponentsBuilder builder =  UriComponentsBuilder.fromHttpUrl(url);
        url = builder.build().toUriString();
        log.info("URL is " + url);

        /* In production
        String url = "https://opensky-network.org/api/states/all";
        UriComponentsBuilder builder =  UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("lamin", model.getAttribute("lamin"))
                .queryParam("lomin", model.getAttribute("lomin"))
                .queryParam("lamax", model.getAttribute("lamax"))
                .queryParam("lomax", model.getAttribute("lomax"));
        url = builder.build().toUriString();
        */

        /* Retrieve results and automatically parse them into a list of Flights */
        APIResult apiResult = restTemplate.getForObject(url, APIResult.class);
        log.info("Success obtaining list of states from OpenSky API");
        //log.info("Results are " + states.getStates());

        return apiResult.getStates();
    }
}
