package com.example.rabbitexample.domain.person;

import com.example.rabbitexample.infrastructure.amqp.RabbitExampleProperties;
import com.example.rabbitexample.rest.ui.model.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PersonQueueProducer {

  private final RabbitTemplate rabbitTemplate;

  private final RabbitExampleProperties rabbitExampleProperties;

  // ich habe mir hier ne Domain Entity gespart. Falls Entity geschickt wird dann domain package, ansonsten Service package
  public void sendPerson(Person aPerson) {
    log.info("SENDING PERSON TO QUEUE !");
    rabbitTemplate.convertAndSend(rabbitExampleProperties.getExchangeName(),rabbitExampleProperties.getOutQueue(), aPerson);
  }

}
