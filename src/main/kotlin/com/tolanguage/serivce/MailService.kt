package com.tolanguage.serivce

import com.tolanguage.model.entity.Mail
import com.tolanguage.repository.MailRepository
import em_notificator.EmNotificator
import em_notificator.MailSenderServiceGrpc.MailSenderServiceBlockingStub
import org.springframework.stereotype.Service

@Service
class MailService(
    val mailRepository: MailRepository,
    val mailSenderStub: MailSenderServiceBlockingStub
) {

    fun send(mail: Mail): Unit =
        with(mail) {
            toMail(this.address)
            mailRepository.save(this)
        }

    fun toMail(address: String): Unit =
        EmNotificator.Mail.newBuilder().setAddress(address).build()
            .run {
                mailSenderStub.sendMail(this)
            }
}