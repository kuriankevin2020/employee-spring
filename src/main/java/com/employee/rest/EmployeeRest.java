package com.employee.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeRest {

    @Value("${com.employee.first-message}")
    private String firstMessage;

    @Value("${com.employee.second-message}")
    private String secondMessage;

    @Value("${com.employee.third-message}")
    private String thirdMessage;

    @GetMapping("/first-message")
    public String getFirstMessage() {
        return firstMessage;
    }

    @GetMapping("/second-message")
    public String getSecondMessage() {
        return secondMessage;
    }

    @GetMapping("/third-message")
    public String getThirdMessage() {
        return thirdMessage;
    }
}
