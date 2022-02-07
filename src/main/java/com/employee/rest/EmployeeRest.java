package com.employee.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeRest {

    @Value("${com.employee.log}")
    private String log;

    @GetMapping("/log")
    public String getLog() {
        return log;
    }
}
