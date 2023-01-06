package com.alexIntervale1.app1.worker.repository.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PersonDto implements Serializable {
    private static final long serialVersionUID = 1;
    private Long messageNumber;
    private String name;
    private String surname;
    private String personalNumber;
}
