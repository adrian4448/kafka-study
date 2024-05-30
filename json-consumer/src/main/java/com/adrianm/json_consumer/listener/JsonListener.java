package com.adrianm.json_consumer.listener;

import com.adrianm.json_consumer.model.Payment;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class JsonListener {

    @KafkaListener(topics = "payment-topic", groupId = "create-group", containerFactory = "jsonContainerFactory")
    public void antiFraud(@Payload Payment payment) throws InterruptedException {
        log.info("Recebi o pagamento ID: {}", payment.getId());
        log.info("Validando fraude ...");

        Thread.sleep(2000);

        log.info("Compra aprovada ...");
    }

    @KafkaListener(topics = "payment-topic", groupId = "pdf-group", containerFactory = "jsonContainerFactory")
    public void pdfGenerator(@Payload Payment payment) throws InterruptedException {
        log.info("Gerando PDF ...");

        Thread.sleep(2000);
    }

    @KafkaListener(topics = "payment-topic", groupId = "email-group", containerFactory = "jsonContainerFactory")
    public void sendEmail(@Payload Payment payment) throws InterruptedException {
        log.info("Enviando email de confirmacao para o usuario {}", payment.getIdUser());
    }
}
