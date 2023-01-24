package com.alexIntervale1.app1.controller;

import com.alexIntervale1.app1.exeption.CustomAppException;
import com.alexIntervale1.app1.repository.dto.PersonDto;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageNotWriteableException;
import javax.jms.TextMessage;
import javax.validation.Valid;
import java.util.HashMap;

@Slf4j
@RestController()
@RequestMapping("/")
@RequiredArgsConstructor
public class RequestController {
    private final JmsTemplate jmsTemplate;
    private final Gson gson;
    private static long count = 0;
    private static final HashMap<String, String> textMessageMap = new HashMap<String, String>();

    @PostMapping("individual_person")
    public ResponseEntity<String> sendMessageIndividualPerson(@Valid @RequestBody PersonDto message) {

        log.debug("Получен запрос " + message);
        try {
            String json = gson.toJson(message);
            log.debug("Преобразованное в json входяўее сообщение " + json);
            String key = String.valueOf(count += 1);

            ActiveMQTextMessage textMessage = getTextMessage(key, json);

            jmsTemplate.convertAndSend("individual.queue", textMessage);
            textMessage.acknowledge();

            String response = getResult(key);

            return new ResponseEntity<>("Запрос успешно отправлен " + json +
                    " ответ на данный запрос " + response, HttpStatus.OK);

        } catch (Exception e) {
            log.warn("Проблема при отправке сообщения в очередь, метод sendMessageIndividualPerson ", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ActiveMQTextMessage getTextMessage(String key, String json) throws CustomAppException {
        ActiveMQTextMessage textMessage = new ActiveMQTextMessage();
        textMessage.setCorrelationId(key);
        try {
            textMessage.setText(json);
        } catch (MessageNotWriteableException e) {
            log.warn("Проблема при добавлении сообщения в textMessage" + json);
            throw new CustomAppException(e);
        }
        log.info("Created TextMessage " + textMessage);
        return textMessage;
    }

    private static String getResult(String key) throws CustomAppException {
        log.info("ключ запроса на ответ " + key);
        while (!textMessageMap.containsKey(key)) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                log.warn("Проблемы при получении результата, метод getResult ", e);
                throw new CustomAppException(e);
            }
        }
        String result = textMessageMap.get(key);
        textMessageMap.remove(key);
        return result;
    }

    @JmsListener(destination = "outindividual.queue")
    private void result(final Message message) throws CustomAppException {
        String key = null;
        try {
            key = message.getJMSCorrelationID();
        } catch (JMSException e) {
            log.warn("Проблемы при получении порядкового номера сообщения , метод result ", e);
            throw new CustomAppException(e);
        }
        log.debug("Порядковый номер отправленного сообщения " + key);
        String body = "";
        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            try {
                body = textMessage.getText();
            } catch (JMSException e) {
                log.warn("Проблемы при получении текста ответа, метод result ", e);
                throw new CustomAppException(e);
            }
        }
        textMessageMap.put(key, body);
    }
}
