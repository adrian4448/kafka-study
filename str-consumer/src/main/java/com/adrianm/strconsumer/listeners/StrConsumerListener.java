package com.adrianm.strconsumer.listeners;

import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class StrConsumerListener {

    // informando uma particao especifica para se inscrever
    @KafkaListener(groupId = "group-0",
            topicPartitions = {
                @TopicPartition(topic = "str-topic", partitions = { "0" })
            },
            containerFactory = "strContainerFactory")
    public void listener(String message) {
       log.info("CREATE ::: receive message: {}", message);
    }

    // desta forma ele se inscreve em todas as particoes
    @KafkaListener(groupId = "group-1", topics = "str-topic", containerFactory = "strContainerFactory")
    public void log(String message) {
        log.info("LOG ::: receive message: {}", message);
    }

    // desta forma cada listener ira se inscrever em uma particao
    @KafkaListener(groupId = "group-2", topics = "str-topic", containerFactory = "strContainerFactory")
    public void history(String message) {
        log.info("LOG ::: receive message: {}", message);
    }

    @KafkaListener(groupId = "group-2", topics = "str-topic", containerFactory = "strContainerFactory")
    public void history2(String message) {
        log.info("LOG ::: receive message: {}", message);
    }

    // com esta containerFactory definida nas configs, ele ira interceptar a mensagem em questao
    @KafkaListener(groupId = "group-3", topics = "str-topic", containerFactory = "validMessageContainerFactory")
    public void validMessage(String message) {
        log.info("LOG ::: receive message: {}", message);
    }

    // desta forma, ele ira utilizar o tratamento de excecao definido no ErrorCustomHandler
    @KafkaListener(groupId = "group-4", topics = "str-topic", containerFactory = "strContainerFactory", errorHandler = "errorCustomHandler")
    public void exception(String message) {
        log.info("LOG ::: receive message: {}", message);
        throw new RuntimeException("Deu ruim");
    }


}
