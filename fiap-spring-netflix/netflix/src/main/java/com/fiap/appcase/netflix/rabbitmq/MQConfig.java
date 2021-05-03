package com.fiap.appcase.netflix.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    public static final String QUEUE = "netflix_messages";
    public static final String QUEUE_LISTENER = "netflix_messages_listener";
    public static final String EXCHANGE_LISTENER = "exchange_listener";
    public static final String EXCHANGE = "exchange";
    public static final String ROUTING_KEY  = "routingKey";

    
    @Bean
    public Queue queue() {
        return new Queue(QUEUE);
    }
    
    @Bean
    public Queue queueListener() {
        return new Queue(QUEUE_LISTENER);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }
    
    @Bean
    public TopicExchange exchangeListener() {
        return new TopicExchange(EXCHANGE_LISTENER);
    }


    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(ROUTING_KEY);
    }
    
    @Bean
    public Binding binding2(Queue queueListener, TopicExchange exchangeListener) {
        return BindingBuilder
                .bind(queueListener)
                .to(exchangeListener)
                .with(ROUTING_KEY);
    }
    
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    
    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }

}
