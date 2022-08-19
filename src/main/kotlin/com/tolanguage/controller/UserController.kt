package com.tolanguage.controller

import com.tolanguage.model.dto.SignInDTO
import com.tolanguage.model.dto.SignUpDTO
import com.tolanguage.model.dto.LoginResponse
import com.tolanguage.serivce.UserService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@CrossOrigin
@RestController
@RequestMapping("/v1/users")
class UserController(
    private val userService: UserService
) {

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    suspend fun signUp(@Valid @RequestBody signUpDTO: SignUpDTO): LoginResponse =
        userService.signUp(signUpDTO)

    @PostMapping("/sign-in")
    suspend fun signIn(@Valid @RequestBody signInDTO: SignInDTO): LoginResponse =
        userService.signIn(signInDTO)
}