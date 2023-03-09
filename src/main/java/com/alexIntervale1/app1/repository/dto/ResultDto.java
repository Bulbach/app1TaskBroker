package com.alexIntervale1.app1.repository.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@Component
@JacksonXmlRootElement(
        namespace = "http://intervale.by/spring",
        localName = "personResponse"
)
public class ResultDto {
    private String name;
    private String surname;
    private long personalNumber;
    private double accrualAmount;
    private double payableAmount;
    private long ordinanceNumber;
    private String dateOfTheDecree;
    private double codeArticle;

}
