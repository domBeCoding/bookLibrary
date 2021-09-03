package uk.codingplayroom.bls.ft.config;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = { BlsContextConfig.class, LocalServiceConfig.class},
        initializers = {ConfigDataApplicationContextInitializer.class})
@CucumberContextConfiguration
public class CucumberConfig {
}
