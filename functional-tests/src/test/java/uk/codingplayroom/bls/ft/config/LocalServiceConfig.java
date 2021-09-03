package uk.codingplayroom.bls.ft.config;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import uk.codingplayroom.bls.service.Application;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Configuration
public class LocalServiceConfig {

    private ConfigurableApplicationContext serviceApplicationContext;

    @PostConstruct
    public void startUp(){ serviceApplicationContext = SpringApplication.run(Application.class);}

    @PreDestroy
    public void shutDown(){serviceApplicationContext.close();}
}
