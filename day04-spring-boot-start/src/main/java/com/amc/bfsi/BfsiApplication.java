package com.amc.bfsi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @SpringBootApplication is three annotations in one:
 *   @Configuration + @EnableAutoConfiguration + @ComponentScan
 *
 * Auto configuration is what removes the XML we would have written in Day 3.
 */
@SpringBootApplication
public class BfsiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BfsiApplication.class, args);
    }
}
