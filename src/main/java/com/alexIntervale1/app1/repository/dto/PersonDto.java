package com.alexIntervale1.app1.repository.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(
        namespace = "http://intervale.ru/spring"
        , name = "person"
)
@Data
public class PersonDto implements Serializable {
    private static final long serialVersionUID = 1;
    @NotBlank
    @Size(max = 20)
    @XmlElement
    private String name;
    @NotBlank
    @Size(max = 30)
    @XmlElement
    private String surname;
    @NotBlank
    @Size(min = 12, max = 12)
    @XmlElement
    private String personalNumber;
}
