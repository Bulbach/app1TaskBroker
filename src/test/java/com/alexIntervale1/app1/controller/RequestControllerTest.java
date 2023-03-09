package com.alexIntervale1.app1.controller;

import com.alexIntervale1.app1.repository.dto.PersonDto;
import com.alexIntervale1.app1.repository.dto.ResultDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class RequestControllerTest {
    @Autowired
    JmsTemplate jmsTemplate;
    @Autowired
    private MockMvc mockMvc;
    Gson gson;
    @Mock
    HashMap<String, String> textMessageMap = new HashMap<String, String>();
    @Autowired
    RequestController requestController;


    private static long count = 0;

        @Test
    void testSendMessageIndividualPerson() {
        ResponseEntity<ResultDto> result = requestController.sendMessageIndividualPerson(new PersonDto());
        Assertions.assertEquals(ResponseEntity.internalServerError().build(), result);
    }
    @Test
    public void whenJavaSerializedToXmlStr_thenCorrect() throws JsonProcessingException {
        XmlMapper xmlMapper = new XmlMapper();
        String xml = xmlMapper.writeValueAsString(new PersonDto());
        assertNotNull(xml);

        System.out.println(xml);
    }

    @Test
    public void checkTypeHttpRequestJson() throws Exception {
        mockMvc.perform(post("/individual_person")
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }
    @Test
    public void checkTypeHttpRequestXml() throws Exception {
        mockMvc.perform(post("/individual_person")
                        .contentType("application/xml"))
                .andExpect(status().isOk());
    }

}
