package com.csu.controller;

import com.csu.component.HelloWorld;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/helloworld")
public class HelloController {

    @Autowired
    private HelloWorld helloWorld;

    @GetMapping
    public String hello() {
        return helloWorld.hello();
    }
}
