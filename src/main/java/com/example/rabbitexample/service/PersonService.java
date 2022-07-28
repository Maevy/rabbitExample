package com.example.rabbitexample.service;

import com.example.rabbitexample.domain.person.PersonQueueProducer;
import com.example.rabbitexample.rest.ui.model.Person;
import java.time.OffsetDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonService {

  private final PersonQueueProducer personQueueProducer;

  public Person findPersonAndPutToQueue(String name, String type) {
    Person person = new Person().inputParam(name).name(name).createdAt(OffsetDateTime.now());
    personQueueProducer.sendPerson(person);
    return person;


  }

}
