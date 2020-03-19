package org.ua.es.labproject.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.ua.es.labproject.kafka.KafkaCons;

/**
 * labproject - LoggerController <br>
 *
 * @author Paulo Vasconcelos paulobvasconcelos@gmail.com
 * @author Pedro Teixeira pedro.teix@ua.pt
 * @version 2.0 - March 11, 2020
 */
@Controller
public class LoggerController {
    private Logger logger = LoggerFactory.getLogger(LoggerController.class);

    @Autowired
    private KafkaCons consumer;

    @GetMapping("/logging")
    public String logging(Model model) {
        logger.info("Consuming logging messages");

        model.addAttribute("logs", consumer.getLogs_messages());
        model.addAttribute("data", consumer.getData_messages());

        return "logging";
    }


}
