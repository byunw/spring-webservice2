package com.jojoldu.webservice2;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Application{

    public static final String APPLICATION_LOCATIONS="spring.config.location="
            +"classpath:src/main/resources/application.yml,"+"src/main/resources/real-application.yml";


    public static void main(String[] args){

        new SpringApplicationBuilder(Application.class)
                .properties(APPLICATION_LOCATIONS)
                .run(args);

    }
}








