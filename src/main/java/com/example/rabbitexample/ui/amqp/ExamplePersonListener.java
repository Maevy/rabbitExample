package com.example.rabbitexample.ui.amqp;

import com.example.rabbitexample.rest.ui.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ExamplePersonListener {


  @RabbitListener(queues = "#{exampleQueue.name}")
  public void consumeExamplePerson(Person aPerson) {
    log.info("The eagle hase landed, its name is:{}", aPerson.getName());
    throw new IllegalArgumentException();
  }


}
