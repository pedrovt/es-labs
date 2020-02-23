package org.ua.es.labproject.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.ua.es.labproject.ScheduledTask;
import org.ua.es.labproject.models.State;
import org.ua.es.labproject.models.Value;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * labproject - TestController <br>
 *
 * @author Pedro Teixeira pedro.teix@ua.pt
 * @version 1.0 - February 22, 2020
 */

@Controller
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    private static final Logger log = LoggerFactory.getLogger(ScheduledTask.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    // lamin=&lomin=5.9962&lamax=47.8229&lomax=10.5226

    @GetMapping("/test")
    public String test(@RequestParam(name="lamin", required=false, defaultValue = "45.8389") double lamin,
                        @RequestParam(name="lomin", required=false, defaultValue = "5.9962") double lomin,
                        @RequestParam(name="lamax", required=false, defaultValue = "47.8229") double lamax,
                        @RequestParam(name="lomax", required=false, defaultValue = "10.5226") double lomax, Model model) {

        log.info("GET - /test - lamin-lomin-lamax-lomax " + lamin + lomin + lamax + lomax);

        model.addAttribute("lamin", lamin);
        model.addAttribute("lomin", lomin);
        model.addAttribute("lamax", lamax);
        model.addAttribute("lomax", lomax);

        log.info("GET - /test - result is " + getStates(model));
        return "test";
    }

    private String getStates(Model model){
        log.info("Aux - getStates");

        String url = "https://opensky-network.org/api/states/all";
        UriComponentsBuilder builder =  UriComponentsBuilder.fromHttpUrl(url)
                                        .queryParam("lamin", model.getAttribute("lamin"))
                                        .queryParam("lomin", model.getAttribute("lomin"))
                                        .queryParam("lamax", model.getAttribute("lamax"))
                                        .queryParam("lomax", model.getAttribute("lomax"));
        url = builder.build().toUriString();
        log.info("Aux - getStates - URI is " + url);

        State state = restTemplate.getForObject(url, State.class);

        log.info("Aux - getStates - state " + state);
        log.info("Aux - getStates - state value" + state.getStates().toString());

        /* parse states info */
        List states = state.getStates();
        String info = states.toString();

        ObjectMapper mapper = new ObjectMapper();
        CollectionType javaType = mapper.getTypeFactory().constructCollectionType(List.class, Value.class);

        try {
            List<Value> statesAsList = mapper.readValue(info, javaType);
            info = statesAsList.toString();
        } catch (JsonProcessingException e) {
            log.error("Error" + e + "\nCouldn't convert states into a list of Values array! Using toString representation");
            info = states.toString();
        }

        model.addAttribute("info", info);
        return state.getStates().toString();
    }

}
