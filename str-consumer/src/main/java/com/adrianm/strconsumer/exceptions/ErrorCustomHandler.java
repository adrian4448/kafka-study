package com.adrianm.strconsumer.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.listener.KafkaListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ErrorCustomHandler implements KafkaListenerErrorHandler {
    @Override
    public Object handleError(Message<?> message, ListenerExecutionFailedException exception) {
        log.error("Ocorreu a excecao com mensagem {}", exception.getRootCause().getMessage());
        log.error("Payload que ocorreu o erro: {}", message.getPayload());
        return null;
    }
}
