package org.ua.es.labproject.kafka.examples;

import org.ua.es.labproject.kafka.KafkaCons;
import org.ua.es.labproject.kafka.KafkaProd;
import java.util.ArrayList;
import java.util.List;

public class Examples {

        public static void consume() {
                KafkaCons k = new KafkaCons();
                List<String> topics = new ArrayList<>();
                topics.add("log");
                k.consume(topics);
        }

        public static void produce() {
            KafkaProd k = new KafkaProd();
            k.send("log","message");
        }


}
