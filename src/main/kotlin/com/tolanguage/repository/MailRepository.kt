package com.tolanguage.repository

import com.tolanguage.model.entity.Mail
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface MailRepository : MongoRepository<Mail, String> {
}