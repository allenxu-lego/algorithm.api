package com.playground.algorithmapi.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlgorithmRequest {
    
    @NotNull(message = "input参数不能为空")
    @Min(value = 1, message = "input必须大于0")
    @Max(value = 100, message = "input不能超过100")
    private Integer input;
    
    @NotNull(message = "nth参数不能为空")
    @Min(value = 1, message = "nth必须大于0")
    @Max(value = 100, message = "nth不能超过100")
    private Integer nth;
}