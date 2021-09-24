package com.tubalec.domain.requests;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@ApiOperation("Class for registration user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestForRegistration {

    @Pattern(message = "Bad formed person name: ${validatedValue}, name must start with an uppercase letter and be 2-30 letters long",
            regexp = "^[A-Z][a-z]+")
    @NotNull
    @Size(min = 2, max = 30)
    private String name;

    @Pattern(message = "Bad formed person surname: ${validatedValue}, surname must start with an uppercase letter and be 2-30 letters long",
            regexp = "^[A-Z][a-z]+")
    @NotNull
    @Size(min = 2, max = 30)
    private String surname;

    @NotNull
    private String address;

    @Pattern(message = "Phone number must start with +375, then - 9 digits",
            regexp = "\\+375[0-9]{9}")
    @NotBlank
    private String telephoneNumber;

    @Size(min=2, max=30)
    @NotNull
    private String login;

    @Size(min=5, max=15)
    @NotNull
    private String password;
}
