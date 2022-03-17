package com.bookmngsys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @author Newway
 */
@SpringBootApplication
@EnableWebMvc
@EnableOpenApi
public class BookMngSysApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookMngSysApplication.class, args);
    }

}
