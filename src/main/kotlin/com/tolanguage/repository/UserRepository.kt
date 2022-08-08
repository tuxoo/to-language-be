package com.tolanguage.repository

import com.tolanguage.model.entity.User
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository : MongoRepository<User, String> {

    fun findByEmailAndPasswordHash(email: String, passwordHash: String): User?

    fun findByEmail(email: String): User?

    fun findOneById(id: String): User?
}