package org.ua.es.labproject.kafka;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/**
 * labproject - KafkaConsumer <br>
 * Thanks to our colleague Margarida <br>
 *
 * @author Paulo Vasconcelos paulobvasconcelos@gmail.com
 * @author Pedro Teixeira pedro.teix@ua.pt
 * @version 2.0 - March 11, 2020
 */
public class KafkaCons {
    /* ############################################################################################################## */
    /* Constants */
    private static final Logger log = LoggerFactory.getLogger(KafkaCons.class);

    /* ############################################################################################################## */
    /* Instance Fields */
    private Properties props = new Properties();
    private Consumer<String, String> consumer;

    /* ############################################################################################################## */
    /* Constructors */
    public KafkaCons() {
        this("localhost:9092");
    }

    public KafkaCons(String bootstrapServers) {
        props.put("bootstrap.servers", bootstrapServers);
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("group.id", "labproject");
        // log4j.logger.org.apache.kafka = WARN;
        consumer = new KafkaConsumer<String, String>(props);
    }

    /* ############################################################################################################## */
    /* Consume */
    public List<String> consume(List<String> topics) {

        consumer.subscribe(topics);
        List<String> messages = new LinkedList<>();

        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(10);
                for (ConsumerRecord<String, String> record : records) {
                    System.out.println(record.value());
                    messages.add(record.value());
                }
            }
        } catch (Exception e) {
            // System.out.println(e.getMessage());
        } finally {
            consumer.close();

        }

        return messages;
    }

}
