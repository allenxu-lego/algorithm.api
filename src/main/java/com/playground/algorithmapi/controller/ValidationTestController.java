package com.playground.algorithmapi.controller;

import java.util.HashMap;
import java.util.Map;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.playground.algorithmapi.dto.ComparisonRequest;
import com.playground.algorithmapi.dto.SimpleComparisonRequest;
import com.playground.algorithmapi.validation.ValidRange;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@RestController
@RequestMapping("/validation")
@Validated
@Tag(name = "验证接口", description = "提供各种参数验证示例的API")
public class ValidationTestController {

    /**
     * 测试基本校验注解
     */
    @GetMapping("/basic")
    @Operation(summary = "基本参数验证", description = "测试基本的参数验证注解，如@NotNull, @Size, @Min, @Max等")
    public Map<String, Object> testBasicValidation(
            @Parameter(name = "name", description = "姓名")
            @RequestParam 
            @NotNull(message = "name不能为空")
            @Size(min = 2, max = 20, message = "name长度必须在2-20之间")
            String name,
            
            @Parameter(name = "age", description = "年龄")
            @RequestParam
            @Min(value = 18, message = "年龄不能小于18")
            @Max(value = 120, message = "年龄不能大于120")
            Integer age,
            
            @Parameter(name = "email", description = "邮箱地址")
            @RequestParam
            @Email(message = "邮箱格式不正确")
            String email) {
        
        Map<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("age", age);
        result.put("email", email);
        result.put("message", "基本参数验证通过");
        return result;
    }

    /**
     * 测试自定义范围校验注解@ValidRange
     */
    @GetMapping("/range")
    @Operation(summary = "范围验证", description = "测试自定义的范围验证注解@ValidRange")
    public Map<String, Object> testRangeValidation(
            @Parameter(name = "value", description = "待验证的值")
            @RequestParam 
            @ValidRange(min = 1, max = 100, message = "value必须在1-100之间")
            Integer value) {
        
        Map<String, Object> result = new HashMap<>();
        result.put("value", value);
        result.put("message", "范围验证通过");
        return result;
    }

    /**
     * 测试正则表达式校验
     */
    @GetMapping("/pattern")
    @Operation(summary = "正则表达式验证", description = "测试正则表达式验证注解@Pattern")
    public Map<String, Object> testPatternValidation(
            @Parameter(name = "phone", description = "手机号码")
            @RequestParam
            @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号码格式不正确")
            String phone) {
        
        Map<String, Object> result = new HashMap<>();
        result.put("phone", phone);
        result.put("message", "正则表达式验证通过");
        return result;
    }

    /**
     * 测试比较校验注解@Valid
     */
    @PostMapping("/comparison")
    @Operation(summary = "比较验证", description = "测试比较验证，验证start必须小于end")
    public Map<String, Object> testComparisonValidation(@Valid @RequestBody ComparisonRequest request) {
        
        Map<String, Object> result = new HashMap<>();
        result.put("minValue", request.getMinValue());
        result.put("maxValue", request.getMaxValue());
        result.put("startDate", request.getStartDate());
        result.put("endDate", request.getEndDate());
        result.put("message", "比较验证通过");
        return result;
    }

    /**
     * 测试简单比较校验注解@Valid
     */
    @PostMapping("/simple-comparison")
    @Operation(summary = "简单比较验证", description = "测试简单比较验证，验证start必须小于end")
    public Map<String, Object> testSimpleComparisonValidation(@Valid @RequestBody SimpleComparisonRequest request) {
        
        Map<String, Object> result = new HashMap<>();
        result.put("scoreA", request.getScoreA());
        result.put("scoreB", request.getScoreB());
        result.put("message", "简单比较验证通过");
        return result;
    }
}