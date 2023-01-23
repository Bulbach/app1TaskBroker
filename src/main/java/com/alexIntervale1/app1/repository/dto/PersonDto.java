package com.alexIntervale1.app1.repository.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class PersonDto implements Serializable {
    private static final long serialVersionUID = 1;
    @NotBlank
    @Size(max = 20)
    private String name;
    @NotBlank
    @Size(max = 30)
    private String surname;
    @NotBlank
    @Size(min=12, max=12)
    private String personalNumber;
}