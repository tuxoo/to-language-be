package com.tolanguage.config.security

import com.tolanguage.serivce.UserService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AppUserDetailsService(
    private val userService: UserService
) : UserDetailsService {

    override fun loadUserByUsername(email: String?): UserDetails =
        AppUserDetails.toAppUserDetails(userService.getUserByEmail(email!!))


    fun loadUserByUserId(id: String): UserDetails =
        AppUserDetails.toAppUserDetails(userService.getUserById(id))

}