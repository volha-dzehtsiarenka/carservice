//package com.degtyarenko.configuration;
//
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
//import org.springframework.kafka.core.DefaultKafkaProducerFactory;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.core.ProducerFactory;
//
//import java.util.Map;
//
///**
// * description
// *
// * @author Degtyarenko Olga
// * @since 18.05.2023
// */
//@Configuration
//@PropertySource("classpath:kafka.properties")
//public class KafkaProducerConfig {
//
//    @Value("${spring.kafka.bootstrap-servers}")
//    private String kafkaServer;
//
//    @Value("${kafka.producer.id}")
//    private String kafkaProducerId;
//
//    @Bean
//    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
//        return new PropertySourcesPlaceholderConfigurer();
//    }
//
//    @Bean
//    public Map<String, Object> producerConfigs() {
//        return Map.of(
//                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer,
//                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class,
//                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class,
//                ProducerConfig.CLIENT_ID_CONFIG, kafkaProducerId
//        );
//    }
//
//    @Bean
//    public ProducerFactory<String, String> producerStringFactory() {
//        return new DefaultKafkaProducerFactory<>(producerConfigs());
//    }
//
//    @Bean
//    public KafkaTemplate<String, String> kafkaTemplate() {
//        return new KafkaTemplate<>(producerStringFactory());
//    }
//
//}
