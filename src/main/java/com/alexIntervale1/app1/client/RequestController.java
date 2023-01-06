package com.alexIntervale1.app1.client;

import com.alexIntervale1.app1.worker.repository.dto.PersonDto;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController()
@RequestMapping("/")
@RequiredArgsConstructor
public class RequestController {
    private final JmsTemplate jmsTemplate;
    private final Gson gson;
    private static long count = 0;
//individual person,juridical person
    @PostMapping("individual_person")
    public String sendMessageIndividualPerson(@RequestBody PersonDto dto) {
        dto.setMessageNumber(count+=1);
        log.info(String.valueOf(dto));
        String json = gson.toJson(dto);
        log.info(json);
        jmsTemplate.convertAndSend("individual.queue", json);
        return "Successful send " + json;
    }
    @PostMapping("juridical_person")
    public ResponseEntity<String> sendMessageJuridicalPerson(@RequestBody PersonDto dto){
            log.info(String.valueOf(dto));
            try{
                dto.setMessageNumber(count+=1);
                String json = gson.toJson(dto);
                log.info(json);
                jmsTemplate.convertAndSend("juridical.queue", json);
                return new ResponseEntity<>("Successful send " + json,HttpStatus.OK);
            }catch (Exception e){
                return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }
//    private JmsTemplate setting(JmsTemplate jmsTemplate){
//        jmsTemplate.set
//        return jmsTemplate;
//    }
}
