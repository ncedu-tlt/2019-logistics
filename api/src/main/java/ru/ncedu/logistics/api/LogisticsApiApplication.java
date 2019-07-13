package ru.ncedu.logistics.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableAsync
@EnableScheduling
@EnableSwagger2
public class LogisticsApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogisticsApiApplication.class, args);
    }

}
