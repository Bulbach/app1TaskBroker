package com.alexIntervale1.app1.repository.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Person implements Serializable {

    private static final long serialVersionUID = 2;
    private String name;
    private String surname;
    private long personalNumber;
}
