package br.com.zuulservice.store;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@EnableConfigurationProperties
@ConfigurationProperties
public class StoreMappingConfig {

    private Map<String, String> storeMapping = new HashMap<String, String>();

    public Map<String, String> getStoreMapping() {
        return storeMapping;
    }

    public void setStoreMapping(Map<String, String> storeMapping) {
        this.storeMapping = storeMapping;
    }
}
