package com.tolanguage.model.dto

import javax.validation.constraints.NotBlank

data class SignUpDTO(
    @NotBlank(message = "firstname is blank") val firstName: String,
    @NotBlank(message = "lastName is blank") val lastName: String,
    @NotBlank(message = "email is blank") val email: String,
    @NotBlank(message = "password is blank") val password: String
)
