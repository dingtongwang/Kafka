package com.csu.controller;

import com.csu.component.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {
  @Autowired
  private Producer producer;

  @GetMapping(value = "/publish")
  public void sendMessageToKafkaTopic(@RequestParam("message") String message){
    this.producer.sendMessage(message);
  }
}
