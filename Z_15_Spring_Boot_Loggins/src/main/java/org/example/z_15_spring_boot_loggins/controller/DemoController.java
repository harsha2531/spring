package org.example.z_15_spring_boot_loggins.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/demo")
public class DemoController {
    private static final Logger log =
            LoggerFactory.getLogger(DemoController.class);

    @GetMapping
    public void logDemoMethod(){
        log.info("trace message");
        log.debug("Customer is deleted by id : ");
        log.info("info message");
        log.warn("warn message");
        log.error("error message");

    }
}
