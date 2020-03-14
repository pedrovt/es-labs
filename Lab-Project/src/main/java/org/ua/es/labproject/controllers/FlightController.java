package org.ua.es.labproject.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.ua.es.labproject.models.APIResult;
import org.ua.es.labproject.models.Flight;
import org.ua.es.labproject.repository.FlightRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * labproject - FlightsController <br>
 *
 * @author Paulo Vasconcelos paulobvasconcelos@gmail.com
 * @author Pedro Teixeira pedro.teix@ua.pt
 * @version 2.0 - March 11, 2020
 */

@Controller
public class FlightController {
    /* ############################################################################################################## */
    /* Constants */
    private static final Logger log = LoggerFactory.getLogger(FlightController.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    /* Instance Fields */
    @Autowired private RestTemplate restTemplate;
    @Autowired private FlightRepository repository;
    private List<Flight> cache;
    private Date date = new Date();

    /* ############################################################################################################## */
    /* View Endpoints */
    @GetMapping("/flights")
    public String flights(@RequestParam(name="lamin", required=false, defaultValue = "45.8389") double lamin,
                          @RequestParam(name="lomin", required=false, defaultValue = "5.9962") double lomin,
                          @RequestParam(name="lamax", required=false, defaultValue = "47.8229") double lamax,
                          @RequestParam(name="lomax", required=false, defaultValue = "10.5226") double lomax,
                          Model model) {

        log.info("Flights - Arguments (lamin-lomin-lamax-lomax) " + lamin + lomin + lamax + lomax);

        model.addAttribute("lamin", lamin);
        model.addAttribute("lomin", lomin);
        model.addAttribute("lamax", lamax);
        model.addAttribute("lomax", lomax);

        /* Update cache if not previously updated */
        if (cache == null) {
            updateFlights();
        }

        model.addAttribute("time", dateFormat.format(date));
        model.addAttribute("flights", cache);

        return "states";
    }

    /* ############################################################################################################## */
    /* Scheduled task */
    @Scheduled(fixedRate = 10000)
    public void updateFlights() {
        log.warn("[Scheduled Task] The time is now {}", dateFormat.format(new Date()));
        log.info("Updating state cache (repository/database) with OpenSky API Info");

        try {
            /* Get states from API */
            List<Flight> resultsFromAPI = getFlightsFromAPI();
            resultsFromAPI = resultsFromAPI.subList(0, resultsFromAPI.size()/3);

            /* Remove old states to avoid database capacity issues */
            for (Flight flight : resultsFromAPI) {
                List<Flight> statesToRemove = repository.findBycallsign(flight.getCallsign());
                repository.deleteAll(statesToRemove);
                repository.saveAndFlush(flight);
            }

            /* Update database update time */
            date = new Date();

            log.info("Updated state cache (repository/database) with OpenSky API Info");

        } catch (Exception e) {
            log.error("ERROR! Error updating state cache (repository/database) with OpenSky API");
            log.error("API not available (" + e.toString() + ")");
            log.warn("No changes to cache");
        }

        cache = repository.findAll();
        cache.sort((o1, o2) -> (int) (o1.getFlightID() - o2.getFlightID()));        // order cache
    }

    private List<Flight> getFlightsFromAPI() {
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

    /* ############################################################################################################## */
    /* API Endpoint */
    @GetMapping("/addFlight")
    public String addFlight(@RequestParam(name="icao", required=false, defaultValue = "5661692D746520666F6465722C204D61726961") String icao,
                            @RequestParam(name="callsgn", required=false, defaultValue = "PQP420") String callsgn,
                            @RequestParam(name="origin", required=false, defaultValue = "Portugal") String origin,
                            @RequestParam(name="time", required=false, defaultValue = "150000000") int time,
                            @RequestParam(name="lat", required=false, defaultValue = "46.2") double lat,
                            @RequestParam(name="lon", required=false, defaultValue = "8.2") double lon,
                            @RequestParam(name="geoalt", required=false, defaultValue = "0.0") double geoalt,
                            @RequestParam(name="vel", required=false, defaultValue = "0.0") double vel, Model model) {

        log.info("Adding a new state to the database");

        Flight newFlight = new Flight();
        newFlight.setIcao24(icao);
        newFlight.setCallsign(callsgn);
        newFlight.setOrigin_country(origin);
        newFlight.setTime_position(time);
        newFlight.setLatitude(lat);
        newFlight.setLongitude(lon);
        newFlight.setGeo_altitude(geoalt);
        newFlight.setVelocity(vel);
        newFlight.setUserCreated(true);
        repository.saveAndFlush(newFlight);

        log.info("Saved State" + newFlight + " to database");

        model.addAttribute("flight", newFlight);

        return "addFlight";
    }
}
