package org.ua.es.labproject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import org.ua.es.labproject.kafka.KafkaCons;
import org.ua.es.labproject.kafka.KafkaProd;

@SpringBootApplication
@EnableScheduling
/**
 * labproject
 *
 * @author Paulo Vasconcelos paulobvasconcelos@gmail.com
 * @author Pedro Teixeira pedro.teix@ua.pt
 * @version 2.0 - March 11, 2020
 */
public class LabprojectApplication {
//public class LabprojectApplication implements CommandLineRunner {

    /* Constants */
    private static final Logger log = LoggerFactory.getLogger(LabprojectApplication.class);

    /* Run without additional web server */
    public static void main(String args[]) {
        SpringApplication.run(LabprojectApplication.class);
    }

    /* Beans */
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public KafkaCons kafkaConsumer() {
        return new KafkaCons();
    }

    @Bean
    public KafkaProd kafkaProducer() {
        return new KafkaProd();
    }

    // @Override
    // public void run(String... args) {

    //     log.info("StartApplication...");

    // }
}