package com.playground.algorithmapi.dto;

import com.playground.algorithmapi.validation.GreaterThan;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@GreaterThan(fieldA = "scoreA", fieldB = "scoreB", customMessage = "分数A必须大于分数B")
public class SimpleComparisonRequest {
    
    @NotNull(message = "分数A不能为空")
    @Min(value = 0, message = "分数A不能小于0")
    @Max(value = 100, message = "分数A不能大于100")
    private Integer scoreA;
    
    @NotNull(message = "分数B不能为空")
    @Min(value = 0, message = "分数B不能小于0")
    @Max(value = 100, message = "分数B不能大于100")
    private Integer scoreB;
}