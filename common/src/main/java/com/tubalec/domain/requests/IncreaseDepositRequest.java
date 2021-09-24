package com.tubalec.domain.requests;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiOperation("Class for creating user's deposit")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncreaseDepositRequest {

    private Float money;
}
