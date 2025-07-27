package com.docker.test.Docker.Test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/")
    public String dockerTest(){
        return "This is the docker test controller.";
    }
}
