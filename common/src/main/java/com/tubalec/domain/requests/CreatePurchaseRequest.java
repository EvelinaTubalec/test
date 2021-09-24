package com.tubalec.domain.requests;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiOperation("Class for creating purchase for authenticated user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePurchaseRequest {

    private Long bookId;
}
