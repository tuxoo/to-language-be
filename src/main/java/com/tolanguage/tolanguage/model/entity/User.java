package com.tolanguage.tolanguage.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tolanguage.tolanguage.model.enums.Role;

import java.time.Instant;
import java.util.UUID;

public record User(
        UUID id,
        String name,
        String loginEmail,
        @JsonIgnore String passwordHash,
        Instant registeredAt,
        Instant visitedAt,
        Role role,
        Boolean isEnabled
) {
}
