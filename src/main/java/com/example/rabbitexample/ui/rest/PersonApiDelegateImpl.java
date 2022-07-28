package com.example.rabbitexample.ui.rest;

import com.example.rabbitexample.rest.ui.PersonApiDelegate;
import com.example.rabbitexample.rest.ui.model.Person;
import com.example.rabbitexample.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class PersonApiDelegateImpl implements PersonApiDelegate {

  private final PersonService personService;

  @Override
  public Mono<ResponseEntity<Person>> getPerson(String personName, String personType, ServerWebExchange exchange) {
    return Mono.just(personService.findPersonAndPutToQueue(personName, personType)).map(ResponseEntity::ok);
  }

//  @Override
//  public ResponseEntity<Person> getPerson(String personName, String personType) {
//    return ResponseEntity.status(200).body(personService.findPersonAndPutToQueue(personName, personType));
//  }
}
