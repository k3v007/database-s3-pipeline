package com.k3v007.databaseS3Pipeline.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Test controller.
 *
 * @author Vivek
 */
@RestController
public class TestController {

    /**
     * Ping string.
     *
     * @return the string
     */
    @GetMapping(value = "/ping")
    public String ping() {
        return HttpStatus.OK.getReasonPhrase();
    }
}
