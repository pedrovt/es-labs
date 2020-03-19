package org.ua.es.labproject.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * labproject - KafkaProducer <br>
 *
 * @author Paulo Vasconcelos paulobvasconcelos@gmail.com
 * @author Pedro Teixeira pedro.teix@ua.pt
 * @version 2.0 - March 11, 2020
 */
@Service
public class KafkaProd {

    /* ############################################################################################################## */
    /* Constants */
    private static final Logger logger = LoggerFactory.getLogger(KafkaCons.class);
    private static final String TOPIC = "users";

    /* ############################################################################################################## */
    /* Instance Fields */
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, String data) {
        logger.info(String.format("#### -> Producing message -> %s", data));
        this.kafkaTemplate.send(topic, data);
    }
}