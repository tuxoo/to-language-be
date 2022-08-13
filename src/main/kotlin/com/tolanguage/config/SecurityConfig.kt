package com.tolanguage.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.tolanguage.config.security.AppAuthenticationEntryPoint
import com.tolanguage.config.security.JwtFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtFilter: JwtFilter,
    private val objectMapper: ObjectMapper
) {

    private val permittedUris = arrayOf(
        "/v1/users/sign-up",
        "/v1/users/sign-in"
    )

    @Bean
    fun filterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        httpSecurity
            .csrf().disable()
            .authorizeRequests()
            .antMatchers(*permittedUris).permitAll()
            .anyRequest().authenticated()
            .and()
            .addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)
            .exceptionHandling()
            .authenticationEntryPoint(AppAuthenticationEntryPoint(objectMapper))
        return httpSecurity.headers().frameOptions().sameOrigin().and().build();
    }
}