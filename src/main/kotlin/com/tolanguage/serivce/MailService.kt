package com.tolanguage.serivce

import com.tolanguage.model.entity.Mail
import com.tolanguage.repository.MailRepository
import org.springframework.stereotype.Service

@Service
class MailService(
    val mailRepository: MailRepository
) {

    fun add(mail: Mail): Unit =
        with(mail) {
            mailRepository.save(this)
        }

}