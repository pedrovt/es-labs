package org.ua.es.labproject.controllers;

import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * labproject - ScheduledTask <br>
 *
 * @author Paulo Vasconcelos paulobvasconcelos@gmail.com
 * @author Pedro Teixeira pedro.teix@ua.pt
 * @version 2.0 - March 11, 2020
 */
@Component
public class ScheduledTasks {

    /* ############################################################################################################## */
    /* Constants */
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    /* Not used */
    @Scheduled(fixedRate = 10000)
    public void updateFlightsScheduled() {

    }
}