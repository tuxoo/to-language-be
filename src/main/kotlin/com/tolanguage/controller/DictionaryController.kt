package com.tolanguage.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/dictionaries")
class DictionaryController {

    @PostMapping()
    suspend fun signIn(): String =
        "Hello"
}