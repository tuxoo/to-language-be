package com.tolanguage.repository

import com.tolanguage.model.User
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository : MongoRepository<User, String> {

    fun findOneById(id: ObjectId): User
}