package org.ua.es.labproject.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * labproject - KafkaProducer <br>
 * Thanks to our colleague Margarida <br>
 *
 * @author Paulo Vasconcelos paulobvasconcelos@gmail.com
 * @author Pedro Teixeira pedro.teix@ua.pt
 * @version 2.0 - March 11, 2020
 */
@Component
public class KafkaProd {
    /* ############################################################################################################## */
    /* Constants */
    private static final Logger log = LoggerFactory.getLogger(KafkaCons.class);

    /* ############################################################################################################## */
    /* Instance Fields */
    private Properties props = new Properties();
    private Producer<String, String> producer;

    /* ############################################################################################################## */
    /* Constructors */
    public KafkaProd() {
        this("localhost:9092");
    }

    public KafkaProd(String bootstrapServers) {
        props.put("bootstrap.servers", bootstrapServers);
        props.put("acks", "all");
        props.put("retries", 1);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("group.id", "labproject");
        producer = new KafkaProducer<String, String>(props);
    }

    /* ############################################################################################################## */
    /* Produce */
    public void send(String topic, String data) {
        producer.send(new ProducerRecord<String, String>(topic, data));
    }

    public void close() {
        producer.close();
    }
}