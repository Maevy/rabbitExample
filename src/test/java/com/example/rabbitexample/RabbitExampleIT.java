package com.example.rabbitexample;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.example.rabbitexample.infrastructure.amqp.RabbitExampleProperties;
import com.example.rabbitexample.rest.ui.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import reactor.test.StepVerifier;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@EnableConfigurationProperties({RabbitExampleProperties.class})
@Testcontainers
@DirtiesContext
class RabbitExampleIT {

  @Autowired
  private WebTestClient webTestClient;

  @Container
  private static final RabbitMQContainer rabbitMq = new RabbitMQContainer(DockerImageName.parse("rabbitmq"));

  @DynamicPropertySource
  static void containerProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.rabbitmq.host", () -> rabbitMq.getHost());
    registry.add("spring.rabbitmq.port", () -> rabbitMq.getMappedPort(5672).toString());
  }

//  @Test
//  void testRest() {
//    webTestClient.get().uri(uriBuilder -> uriBuilder.path("/person").queryParam("person_name", "Jackson").build())
//        .exchange().expectStatus().isOk().expectBody()
//        .jsonPath("$.name").isEqualTo("Jackson")
//        .jsonPath("$.createdAt").isNotEmpty()
//        .jsonPath("$.inputParam").isEqualTo("Jackson");
//  }

    @Test
  void testRestReactive() {
    webTestClient.get().uri(uriBuilder -> uriBuilder.path("/person").queryParam("person_name", "Jackson").build())
        .exchange().expectStatus().isOk().returnResult(Person.class).getResponseBody().as(StepVerifier::create)
        .assertNext(person -> assertThat(person.getName(), is(equalTo("Jackson")))).verifyComplete();
  }

}
