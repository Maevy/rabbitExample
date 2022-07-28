package com.example.rabbitexample;

import com.example.rabbitexample.infrastructure.amqp.RabbitExampleProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({RabbitExampleProperties.class})
public class RabbitExampleApplication {

  public static void main(String[] args) {
    SpringApplication.run(RabbitExampleApplication.class, args);
  }

}
