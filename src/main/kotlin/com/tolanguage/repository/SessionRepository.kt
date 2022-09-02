package com.tolanguage.repository

import com.tolanguage.model.entity.Session
import com.tolanguage.model.entity.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface SessionRepository : MongoRepository<Session, String> {

    fun findAllByUser(user: User): List<Session>
}