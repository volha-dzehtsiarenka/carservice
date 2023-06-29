//package com.degtyarenko.configuration;
//
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//
///**
// * Описание класса
// *
// * @author Volha Dzehtsiarenka
// * @since 29.06.2023
// */
//@Getter
//@Setter
//@NoArgsConstructor
//@ConfigurationProperties(prefix = "kafka")
//public class KafkaProperties {
//    private Producer producer;
//    private Topic topic;
//    private BootstrapServers bootstrapServers;
//
//    @Getter
//    @Setter
//    @NoArgsConstructor
//    public static class Producer {
//        private Integer id;
//    }
//
//    @Getter
//    @Setter
//    @NoArgsConstructor
//    public static class Topic {
//        private Request request;
//        private Token token;
//
//        @Getter
//        @Setter
//        @NoArgsConstructor
//        public static class Request {
//            private String log;
//        }
//
//        @Getter
//        @Setter
//        @NoArgsConstructor
//        public static class Token {
//            private Grant grant;
//
//            @Getter
//            @Setter
//            @NoArgsConstructor
//            public static class Grant {
//                private String log;
//            }
//        }
//    }
//
//    @Getter
//    @Setter
//    @NoArgsConstructor
//    public static class BootstrapServers {
//        private String bootstrapServers;
//    }
//}
