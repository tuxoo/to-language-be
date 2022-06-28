package com.tolanguage.tolanguage;

import com.tolanguage.tolanguage.config.property.ApplicationProperty;
import com.tolanguage.tolanguage.config.property.CacheProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ApplicationProperty.class, CacheProperty.class})
public class TolanguageApplication {

    public static void main(String[] args) {
        SpringApplication.run(TolanguageApplication.class, args);
    }

}
