package uk.codingplayroom.bls.actuator;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ConfigInfoContributor implements InfoContributor {

    @Override
    public void contribute(Info.Builder builder) {
        Map<String, String> configMap = new HashMap<>();
        configMap.put("appName", "Book Library Service");

        builder.withDetail("config", configMap);
    }
}
