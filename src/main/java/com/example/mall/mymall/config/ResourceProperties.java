package com.example.mall.mymall.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.resources", ignoreUnknownFields = false)
public class ResourceProperties {
    
    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
        "classpath:/META-INF/resources",
        "classpath:/resources/",
        "classpath:/static/",
        "classpath:/public/",
    };

    private String[] staticLocations = CLASSPATH_RESOURCE_LOCATIONS;

    public String[] getStaticLocations() {
        return this.staticLocations;
    }
}
