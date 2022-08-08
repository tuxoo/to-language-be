package com.tolanguage.config.security

import com.tolanguage.model.entity.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class AppUserDetails(
    private val id: String,
    private val login: String,
    private val grantedAuthorities: MutableCollection<out GrantedAuthority>
) : UserDetails {
    companion object {
        fun toAppUserDetails(user: User) =
            with(user)
            {
                AppUserDetails(
                    id = user.id.toString(),
                    login = user.email,
                    grantedAuthorities = mutableListOf(SimpleGrantedAuthority(user.role.name))
                )
            }
    }

    fun getId(): String = id

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = grantedAuthorities

    override fun getPassword(): String = ""

    override fun getUsername(): String = login

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}