package org.ua.es.labproject.models;

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
import org.ua.es.labproject.ScheduledTask;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * labproject - StatesController <br>
 *
 * @author Pedro Teixeira pedro.teix@ua.pt
 * @version 1.0 - February 22, 2020
 */

@Controller
public class StatesController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StateRepository repository;

    private List<State> cache;

    private static final Logger log = LoggerFactory.getLogger(ScheduledTask.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    // lamin=&lomin=5.9962&lamax=47.8229&lomax=10.5226
    @GetMapping("/states")
    public String states(@RequestParam(name="lamin", required=false, defaultValue = "45.8389") double lamin,
                         @RequestParam(name="lomin", required=false, defaultValue = "5.9962") double lomin,
                         @RequestParam(name="lamax", required=false, defaultValue = "47.8229") double lamax,
                         @RequestParam(name="lomax", required=false, defaultValue = "10.5226") double lomax, Model model) {

        log.info("GET - /states - lamin-lomin-lamax-lomax " + lamin + lomin + lamax + lomax);

        model.addAttribute("lamin", lamin);
        model.addAttribute("lomin", lomin);
        model.addAttribute("lamax", lamax);
        model.addAttribute("lomax", lomax);

        if(cache == null) {
            updateStateInfo();
        }
        statesToModel(model);
        return "states";
    }

    private States getStates(Model model){
        //log.info("GET - /states - getStates");
        String url = "https://opensky-network.org/api/states/all";
        UriComponentsBuilder builder =  UriComponentsBuilder.fromHttpUrl(url)
                                        .queryParam("lamin", model.getAttribute("lamin"))
                                        .queryParam("lomin", model.getAttribute("lomin"))
                                        .queryParam("lamax", model.getAttribute("lamax"))
                                        .queryParam("lomax", model.getAttribute("lomax"));
        url = builder.build().toUriString();
        //log.info("GET - /states - getStates - URL is " + url);
        return restTemplate.getForObject(url, States.class);
    }

    public void statesToModel (Model model) {
        model.addAttribute("time", dateFormat.format(date));
        model.addAttribute("states", cache);
    }

    public List<State> getListOfStates() {
        //log.info("GET - /getListOfStates");
        String url = "https://opensky-network.org/api/states/all?lamin=45.8389&lomin=5.0&lamax=47.8229&lomax=11.5226";
        UriComponentsBuilder builder =  UriComponentsBuilder.fromHttpUrl(url);
        url = builder.build().toUriString();
        //log.info("GET - /states - getListOfStates - URL is " + url);
        States states = restTemplate.getForObject(url, States.class);

        //log.info("GET - /states - getListOfStates - results are " + states.getStates());
        return states.getStates();
    }


    Date date = new Date();

    @Scheduled(fixedRate = 10000)
    public void updateStateInfo() {
        log.info("Updating State DB from API Info");

        try {
            List<State> resultsFromAPI = getListOfStates();
            resultsFromAPI = resultsFromAPI.subList(0, resultsFromAPI.size()/3);
            for (State state : resultsFromAPI) {
                List<State> statesToRemove = repository.findBycallsign(state.getCallsign());
                repository.deleteAll(statesToRemove);
                repository.saveAndFlush(state);
            }
            log.info("Updated DB");
            date = new Date();
        } catch (Exception e) {
            log.error("API not available ({}). Using DB Values".format(e.getMessage()));
        }
        cache = repository.findAll();
        cache.sort(new Comparator<State>() {
            @Override
            public int compare(State o1, State o2) {
                return (int) (o1.getFlightID() - o2.getFlightID());
            }
        });
    }

    @GetMapping("/addState")
    public String addState( @RequestParam(name="icao", required=false, defaultValue = "5661692D746520666F6465722C204D61726961") String icao,
                            @RequestParam(name="callsgn", required=false, defaultValue = "PQP420") String callsgn,
                            @RequestParam(name="origin", required=false, defaultValue = "Portugal") String origin,
                            @RequestParam(name="time", required=false, defaultValue = "150000000") int time,
                            @RequestParam(name="lat", required=false, defaultValue = "46.2") double lat,
                            @RequestParam(name="lon", required=false, defaultValue = "8.2") double lon,
                            @RequestParam(name="geoalt", required=false, defaultValue = "0.0") double geoalt,
                            @RequestParam(name="vel", required=false, defaultValue = "0.0") double vel, Model model) {

        log.info("GET - /addState");

        State exampleState = new State();
        exampleState.setIcao24(icao);
        exampleState.setCallsign(callsgn);
        exampleState.setOrigin_country(origin);
        exampleState.setTime_position(time);
        exampleState.setLatitude(lat);
        exampleState.setLongitude(lon);
        exampleState.setGeo_altitude(geoalt);
        exampleState.setVelocity(vel);
        exampleState.setUserCreated(true);
        repository.saveAndFlush(exampleState);

        log.info("Saved State to DB");

        model.addAttribute("state", exampleState);
        return "addState";
    }
}
