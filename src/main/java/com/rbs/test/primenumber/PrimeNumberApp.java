package com.rbs.test.primenumber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableAutoConfiguration
@EnableSwagger2
@ComponentScan("com.rbs.test.primenumber")
public class PrimeNumberApp {

    public static void main(String[] args) {
        SpringApplication.run(PrimeNumberApp.class, args);
    }
}
