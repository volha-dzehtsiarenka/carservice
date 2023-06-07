package com.degtyarenko.aspect;


import com.degtyarenko.kafka.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;

/**
 * description
 *
 * @author Degtyarenko Olga
 * @since 06.06.2023
 */

@Aspect
@Component
@RequiredArgsConstructor
public class RequestLoggingAspect {

    private static final String TYPE_URL = "type:url ";
    private final HttpServletRequest httpServletRequest;
    private final KafkaProducerService kafkaProducerService;

    @SneakyThrows
    @Before(value = "within(com.degtyarenko.controller..*)")
    public void logRequestAndResponse() {
        String requestDetails = TYPE_URL + httpServletRequest.getMethod() + " " +
                ServletUriComponentsBuilder.fromRequest(httpServletRequest).toUriString();
        kafkaProducerService.logRequest(requestDetails);
    }

}
