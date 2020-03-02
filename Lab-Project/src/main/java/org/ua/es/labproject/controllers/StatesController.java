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
import org.ua.es.labproject.ScheduledTask;
import org.ua.es.labproject.models.State;
import org.ua.es.labproject.models.States;

import java.text.SimpleDateFormat;
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

    private States cache;

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

        if(cache==null) {
            cache = getStates(model);
        }
        statesToModel(cache,model);
        return "states";
    }

    private States getStates(Model model){
        log.info("GET - /states - getStates");
        String url = "https://opensky-network.org/api/states/all";
        UriComponentsBuilder builder =  UriComponentsBuilder.fromHttpUrl(url)
                                        .queryParam("lamin", model.getAttribute("lamin"))
                                        .queryParam("lomin", model.getAttribute("lomin"))
                                        .queryParam("lamax", model.getAttribute("lamax"))
                                        .queryParam("lomax", model.getAttribute("lomax"));
        url = builder.build().toUriString();
        log.info("GET - /states - getStates - URL is " + url);
        return restTemplate.getForObject(url, States.class);
    }

    public void statesToModel (States states, Model model) {
        model.addAttribute("time", states.getTime());
        model.addAttribute("states", states.getStates());
    }

    @Scheduled(fixedRate = 10000)
    public void updateStateInfo() {
        log.info("Cleaning cache");
        cache = null;
    }

}
