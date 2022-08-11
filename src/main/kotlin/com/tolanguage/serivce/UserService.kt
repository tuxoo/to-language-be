package com.tolanguage.serivce

import com.github.benmanes.caffeine.cache.Cache
import com.tolanguage.config.security.JwtProvider
import com.tolanguage.model.dto.SignInDTO
import com.tolanguage.model.dto.SignUpDTO
import com.tolanguage.model.dto.TokenContainer
import com.tolanguage.model.entity.User
import com.tolanguage.repository.UserRepository
import com.tolanguage.util.HashUtils
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val jwtProvider: JwtProvider,
    private val userCache: Cache<String, User>,
    private val sessionService: SessionService
) {

    fun signUp(signUpDTO: SignUpDTO): TokenContainer =
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

    fun signIn(signInDTO: SignInDTO): TokenContainer {
        val user = userRepository.findByEmailAndPasswordHash(signInDTO.email, HashUtils.hashSHA1(signInDTO.password))
            ?: error("")

        val accessToken = jwtProvider.generateToken(user.id.toString())

        val refreshToken = sessionService.createSession(user)

        userCache.put(user.id.toString(), user)

        return TokenContainer(
            accessToken,
            refreshToken
        )
    }

    fun getUserById(id: String) =
        userRepository.findOneById(id)
            ?: error("")

    fun getUserByEmail(email: String) =
        userRepository.findByEmail(email)
            ?: error("")
}
