//package com.degtyarenko.service.kafka;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//
///**
// * description
// *
// * @author Degtyarenko Olga
// * @since 18.05.2023
// */
//@Service
//@RequiredArgsConstructor
//public class KafkaProducerService {
//
//    private final KafkaTemplate<String, String> kafkaTemplate;
//
//    private String kafkaTopicRequestLog;
//
//    private String kafkaTopicTokenGrantLog;
//
//    public void logRequest(String request) {
//        kafkaTemplate.send(kafkaTopicRequestLog, request);
//    }
//
//    public void logTokenGrant(String message) {
//        kafkaTemplate.send(kafkaTopicTokenGrantLog, message);
//    }
//
//}
