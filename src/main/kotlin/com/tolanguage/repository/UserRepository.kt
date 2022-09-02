package com.tolanguage.repository

import com.tolanguage.model.entity.User
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : MongoRepository<User, String> {

    fun findByEmailAndPasswordHash(email: String, passwordHash: String): User?

    fun findByEmail(email: String): User?

    fun findOneById(id: ObjectId): User?
}