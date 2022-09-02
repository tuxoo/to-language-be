package com.tolanguage.controller

import com.tolanguage.model.dto.SignInDTO
import com.tolanguage.model.dto.SignUpDTO
import com.tolanguage.model.dto.LoginResponse
import com.tolanguage.serivce.UserService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/v1/users")
class UserController(
    private val userService: UserService
) {

    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Sign up", description = "This method registers a new user")
    @PostMapping("/sign-up")
    fun signUp(@Valid @RequestBody signUpDTO: SignUpDTO): LoginResponse =
        userService.signUp(signUpDTO)

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Sign in", description = "This method authenticates the user")
    @PostMapping("/sign-in")
    fun signIn(@Valid @RequestBody signInDTO: SignInDTO): LoginResponse =
        userService.signIn(signInDTO)
}