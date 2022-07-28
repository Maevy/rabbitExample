package com.example.rabbitexample.ui.rest;

import com.example.rabbitexample.rest.ui.PersonApiDelegate;
import com.example.rabbitexample.rest.ui.model.Person;
import com.example.rabbitexample.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonApiDelegateImpl implements PersonApiDelegate {

  private final PersonService personService;

  @Override
  public ResponseEntity<Person> getPerson(String personName, String personType) {
    return ResponseEntity.status(200).body(personService.findPersonAndPutToQueue(personName, personType));
  }
}
