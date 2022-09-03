package com.tolanguage.config

import com.tolanguage.config.property.MailServiceProperty
import em_notificator.MailSenderServiceGrpc.MailSenderServiceBlockingStub
import em_notificator.MailSenderServiceGrpc.newBlockingStub
import io.grpc.ManagedChannelBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MailSenderConfig(
    val property: MailServiceProperty
) {

    @Bean
    fun mailSenderStub(): MailSenderServiceBlockingStub =
        ManagedChannelBuilder.forAddress(property.host, property.port)
            .usePlaintext()
            .build()
            .run {
                newBlockingStub(this)
            }
}