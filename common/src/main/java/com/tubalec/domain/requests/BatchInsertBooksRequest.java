package com.tubalec.domain.requests;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiOperation("Class for batch insert books")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BatchInsertBooksRequest {

    private int count;
}
