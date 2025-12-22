package com.example.accountservice.infrastructure.config;

import org.springframework.amqp.core.TopicExchange;

import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    public static final String EXCHANGE_NAME="account.exchange";

    @Bean
    public TopicExchange accountExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public JacksonJsonMessageConverter producerJacksonMessageConverter() {
        return new JacksonJsonMessageConverter();
    }
}
