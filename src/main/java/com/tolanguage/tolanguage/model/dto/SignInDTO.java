package com.tolanguage.tolanguage.model.dto;

import javax.validation.constraints.NotBlank;

public record SignInDTO(@NotBlank String email, @NotBlank String password) {
}
