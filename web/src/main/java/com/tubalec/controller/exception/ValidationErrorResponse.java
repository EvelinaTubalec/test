package com.tubalec.controller.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ValidationErrorResponse {

    private List<ErrorMessage> errorMessages = new ArrayList<>();
}
