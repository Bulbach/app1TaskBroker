package com.alexIntervale1.app1.repository.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PersonDto implements Serializable {
    private static final long serialVersionUID = 1;
    private String name;
    private String surname;
    private String personalNumber;
}
