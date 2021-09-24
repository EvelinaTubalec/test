package com.tubalec.domain.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationResponse {

    private String name;

    private String surname;

    private String address;

    private String telephoneNumber;

    private Float money;
}
