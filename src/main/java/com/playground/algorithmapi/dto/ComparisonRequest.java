package com.playground.algorithmapi.dto;

import com.playground.algorithmapi.validation.GreaterThan;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@GreaterThan(fieldA = "maxValue", fieldB = "minValue", customMessage = "最大值必须大于最小值")
@GreaterThan(fieldA = "endDate", fieldB = "startDate", customMessage = "结束日期必须大于开始日期")
public class ComparisonRequest {
    
    @NotNull(message = "最小值不能为空")
    @Min(value = 0, message = "最小值不能小于0")
    private Integer minValue;
    
    @NotNull(message = "最大值不能为空")
    @Min(value = 0, message = "最大值不能小于0")
    private Integer maxValue;
    
    @NotNull(message = "开始日期不能为空")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "日期格式必须为yyyy-MM-dd")
    private String startDate;
    
    @NotNull(message = "结束日期不能为空")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "日期格式必须为yyyy-MM-dd")
    private String endDate;
    
    // 可以添加更多需要比较的字段
    private Double scoreA;
    private Double scoreB;
}