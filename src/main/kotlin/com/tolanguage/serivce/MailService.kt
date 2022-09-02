package com.tolanguage.serivce

import com.tolanguage.model.entity.Mail
import com.tolanguage.repository.MailRepository
import em_notificator.EmNotificator
import em_notificator.MailSenderServiceGrpc
import io.grpc.ManagedChannelBuilder
import org.springframework.stereotype.Service

@Service
class MailService(
    val mailRepository: MailRepository
) {

    fun send(mail: Mail): Unit =
        with(mail) {
            mailRepository.save(this)
            toMail(this.address)
        }

    fun toMail(address: String) {
        val channel = ManagedChannelBuilder.forAddress("localhost", 8088)
            .usePlaintext()
            .build()

        val stub = MailSenderServiceGrpc.newBlockingStub(channel)
        val em = EmNotificator.Mail.newBuilder().setAddress(address).build();

        val resp = stub.sendMail(em)
    }
}