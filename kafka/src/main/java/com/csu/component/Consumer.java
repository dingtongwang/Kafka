package com.csu.component;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

  @KafkaListener(topics = "users", groupId = "group_id")
  public void consume(String message){
    System.out.println("-------------------------------------Consumer: " + message + "--------------------");
  }
}
