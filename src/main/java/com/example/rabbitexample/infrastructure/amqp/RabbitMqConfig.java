package com.example.rabbitexample.infrastructure.amqp;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class RabbitMqConfig {

  public static final String DEFAULT_DIRECT_EXCHANGE = "";


  @Bean
  MessageConverter jsonConverter(final ObjectMapper objectMapper) {
    return new Jackson2JsonMessageConverter(objectMapper);
  }

  @Configuration
  @RequiredArgsConstructor
  public class ExampleConfig {

    private final RabbitExampleProperties rabbitExampleProperties;

    @Bean
    DirectExchange directExchange() {
      return new DirectExchange(rabbitExampleProperties.getExchangeName());
    }

    @Bean
    Queue exampleQueue() {
      return QueueBuilder.durable(rabbitExampleProperties.getInQueue())
          .deadLetterExchange(directExchange().getName())
          .deadLetterRoutingKey(exampleQueueDl().getName())
          .build();
    }
    @Bean
    Binding queueExchangeBinding(final DirectExchange directExchange, final Queue exampleQueue, final AmqpAdmin amqpAdmin) {
      final Binding binding = BindingBuilder.bind(exampleQueue).to(directExchange).with(rabbitExampleProperties.getOutQueue());
      if (directExchange.shouldDeclare()) {
        amqpAdmin.declareExchange(directExchange);
      }
      amqpAdmin.declareQueue(exampleQueue);
      amqpAdmin.declareBinding(binding);
      return binding;
    }

    @Bean
    Queue exampleQueueDl() {
      return QueueBuilder.durable(rabbitExampleProperties.getDlqQueue()).build();
    }
  }
}
