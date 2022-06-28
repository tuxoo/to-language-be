package com.tolanguage.tolanguage.model.error;

import java.time.Instant;

public record ErrorResponse(
        String message,
        String errorTime
) {
}
