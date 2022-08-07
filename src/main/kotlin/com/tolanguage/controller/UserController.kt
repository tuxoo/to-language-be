package com.tolanguage.controller

import com.tolanguage.model.User
import com.tolanguage.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.time.Instant

@RestController
@RequestMapping("/v1/users")
class UserController(
    private val userRepository: UserRepository
) {

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    suspend fun signUp(): String {
        val user = User(
            firstName = "eugen",
            lastName = "krivtsov",
            email = "kia@ya.ru",
            passwordHash = "asdf",
            registeredAt = Instant.now(),
            visitedAt = Instant.now()
        )
//        userRepository.save(user);
        return "hello"
    }
}