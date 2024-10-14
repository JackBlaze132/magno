package com.unibague.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class HelloWorldController {

    @GetMapping(path = "/hello/header", headers = "X-API-VERSION=1")
    public String getFirstVersionOfPersonRequestHeader() {
        return "Hello World V1";
    }

    @GetMapping(path = "/hello/header", headers = "X-API-VERSION=2")
    public String getSecondVersionOfPersonRequestHeader() {
        return "Hello World V2";
    }
}
