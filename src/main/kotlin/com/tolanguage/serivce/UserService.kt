package com.tolanguage.serivce

import com.tolanguage.model.User
import com.tolanguage.model.dto.SignUpDTO
import com.tolanguage.repository.UserRepository
import com.tolanguage.util.HashUtils
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {

    fun signUp(signUpDTO: SignUpDTO) =
        User(
            firstName = signUpDTO.firstName,
            lastName = signUpDTO.lastName,
            email = signUpDTO.email,
            passwordHash = HashUtils.hashSHA1(signUpDTO.password)
        ).run {
            userRepository.insert(this)
        }
}
