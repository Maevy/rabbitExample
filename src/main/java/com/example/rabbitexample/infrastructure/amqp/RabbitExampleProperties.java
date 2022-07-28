package com.example.rabbitexample.infrastructure.amqp;


import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "rabbitexample.amqp")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RabbitExampleProperties {

  @NotEmpty
  private String inQueue;
  @NotEmpty
  private String dlqQueue;
  @NotEmpty
  private String outQueue;
  @NotEmpty
  private String exchangeName;


}
