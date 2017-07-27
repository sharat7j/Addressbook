package com.addressbook;

/**
 * Created by sharatjagannath on 7/24/17.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.addressbook.repository")
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.addressbook.model"})
@ComponentScan(basePackages = {"com.addressbook"})
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
