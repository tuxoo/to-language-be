package com.tolanguage.config

import com.github.benmanes.caffeine.cache.Cache
import com.github.benmanes.caffeine.cache.Caffeine
import com.tolanguage.config.property.CacheProperty
import com.tolanguage.model.entity.User
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CacheConfig(
    private val property: CacheProperty
) {

    @Bean
    fun userCache(): Cache<String, User> = Caffeine.newBuilder()
        .maximumSize(property.userMaximumSize)
        .expireAfterAccess(property.userExpiredTime)
        .build();
}