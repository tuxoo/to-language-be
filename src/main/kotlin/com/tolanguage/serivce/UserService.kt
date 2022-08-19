package com.tolanguage.serivce

import com.github.benmanes.caffeine.cache.Cache
import com.tolanguage.config.security.JwtProvider
import com.tolanguage.model.dto.LoginResponse
import com.tolanguage.model.dto.SignInDTO
import com.tolanguage.model.dto.SignUpDTO
import com.tolanguage.model.dto.UserDto
import com.tolanguage.model.entity.User
import com.tolanguage.model.exception.EntityNotFoundException
import com.tolanguage.repository.UserRepository
import com.tolanguage.util.AuthUtils
import com.tolanguage.util.HashUtils
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val jwtProvider: JwtProvider,
    private val userCache: Cache<String, User>,
    private val sessionService: SessionService
) {

    fun signUp(signUpDTO: SignUpDTO): LoginResponse =
        User(
            firstName = signUpDTO.firstName,
            lastName = signUpDTO.lastName,
            email = signUpDTO.email,
            passwordHash = HashUtils.hashSHA1(signUpDTO.password)
        ).run {
            userRepository.insert(this)
        }.run {
            signIn(
                signInDTO = SignInDTO(signUpDTO.email, signUpDTO.password)
            )
        }

    fun signIn(signInDTO: SignInDTO): LoginResponse {
        val user = userRepository.findByEmailAndPasswordHash(signInDTO.email, HashUtils.hashSHA1(signInDTO.password))
            ?: throw EntityNotFoundException("User not found by credentials [$signInDTO.email, ${signInDTO.password}]")

        val accessToken = jwtProvider.generateToken(user.id.toString())

        val refreshToken = sessionService.createSession(user)

        return UserDto(
            firstName = user.firstName,
            lastName = user.lastName,
            email = user.email,
            registeredAt = user.registeredAt
        ).run {
            LoginResponse(
                accessToken,
                refreshToken,
                this
            )
        }
    }

    fun getUserById(id: String): User =
        userCache.get(id) {
            userRepository.findOneById(id)
                ?: throw EntityNotFoundException("User not found by id [$id]")
        }


    fun getUserByEmail(email: String): User =
        userRepository.findByEmail(email)
            ?: throw EntityNotFoundException("User not found by email [$email]")

    fun getCurrent(): User = getUserById(AuthUtils.getCurrentUser().getId())
}
