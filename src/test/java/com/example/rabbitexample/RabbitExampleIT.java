package com.example.rabbitexample;


import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.example.rabbitexample.infrastructure.amqp.RabbitExampleProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
@SpringBootTest(webEnvironment = RANDOM_PORT)
@EnableConfigurationProperties({RabbitExampleProperties.class})
public class RabbitExampleIT {

  @Autowired
  private WebTestClient webTestClient;

}
