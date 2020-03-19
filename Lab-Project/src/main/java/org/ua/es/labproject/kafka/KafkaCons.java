package org.ua.es.labproject.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * labproject - KafkaConsumer <br>
 * Thanks to our colleague Margarida <br>
 *
 * @author Paulo Vasconcelos paulobvasconcelos@gmail.com
 * @author Pedro Teixeira pedro.teix@ua.pt
 * @version 2.0 - March 11, 2020
 */
@Service
public class KafkaCons {

    /* ############################################################################################################## */
    /* Constants */
    private final Logger logger = LoggerFactory.getLogger(KafkaCons.class);

    /* ############################################################################################################## */
    /* Instance Fields */
    private List<String> logs_messages = new LinkedList<>();
    private List<String> data_messages = new LinkedList<>();

    public List<String> getLogs_messages() {
        return logs_messages;
    }

    public List<String> getData_messages() {
        return data_messages;
    }

    @KafkaListener(topics = "logs", groupId = "labproject")
    private void consumeLogs(String message) throws IOException {
        logger.info(String.format("#### -> Consumed message -> %s", message));
        logs_messages.add(message);
    }

    @KafkaListener(topics = "data", groupId = "labproject")
    private void consumeData(String message) throws IOException {
        logger.info(String.format("#### -> Consumed message -> %s", message));
        data_messages.add(message);
    }
}
