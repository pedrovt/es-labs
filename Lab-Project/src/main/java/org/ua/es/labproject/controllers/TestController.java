package org.ua.es.labproject.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.ua.es.labproject.ScheduledTask;
import org.ua.es.labproject.models.PublicAPI;

import java.text.SimpleDateFormat;

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

    @GetMapping("/test")
    public String test(@RequestParam(name="time", required=false) String time, Model model) {
        log.info("GET - /test - time is " + time);
        model.addAttribute("time", time);
        /* TODO add arguments */

        log.info("GET - /test - result is " + getPublicRandomAPI(model));
        return "test";
    }

    private String getPublicRandomAPI(Model model){
        log.info("Aux - getState");
        PublicAPI publicAPI = restTemplate.getForObject(
                "https://api.publicapis.org/random", PublicAPI.class);
        log.info("Aux - getState - state " + publicAPI);
        log.info("Aux - getState - state value" + publicAPI.getEntries().toString());

        model.addAttribute("info", publicAPI.getEntries().toString());
        return publicAPI.getEntries().toString();
    }

}
