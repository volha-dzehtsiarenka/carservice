//package com.degtyarenko.configuration;
//
//import org.apache.kafka.clients.admin.AdminClientConfig;
//import org.apache.kafka.clients.admin.NewTopic;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.core.KafkaAdmin;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * description
// *
// * @author Degtyarenko Olga
// * @since 18.05.2023
// */
//@Configuration
//public class KafkaTopicConfig {
//
//    private String kafkaServer;
//
//    private String kafkaTopicRequestLog;
//
//    private String kafkaTopicTokenGrantLog;
//
//    @Bean
//    public KafkaAdmin kafkaAdmin() {
//        Map<String, Object> properties = new HashMap<>();
//        properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
//        return new KafkaAdmin(properties);
//    }
//
//    @Bean
//    public NewTopic requestLogTopic() {
//        return new NewTopic(kafkaTopicRequestLog, 1, (short) 1);
//    }
//
//    @Bean
//    public NewTopic tokenGrantLogTopic() {
//        return new NewTopic(kafkaTopicTokenGrantLog, 1, (short) 1);
//    }
//
//}
