package com.tolanguage.tolanguage.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.tolanguage.tolanguage.config.property.CacheProperty;
import com.tolanguage.tolanguage.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@EnableCaching
@Configuration
@RequiredArgsConstructor
public class CacheConfig {
    private final CacheProperty cacheProperty;

    @Bean
    public Cache<UUID, User> cache() {
        return Caffeine.newBuilder()
                .maximumSize(cacheProperty.userMaximumSize())
                .expireAfterAccess(cacheProperty.userExpiredTimeHours(), TimeUnit.HOURS)
                .build();
    }
}
