package com.tolanguage.model.enums

enum class Auth(
    val value: String,
    val length: Int
) {
    AUTHORIZATION("Authorization", 13),
    BEARER("Bearer ", 7),
    BASIC("Basic ", 6),
    JWT("JWT", 3)
}