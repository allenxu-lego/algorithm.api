package com.playground.algorithmapi.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidRangeValidator implements ConstraintValidator<ValidRange, Integer> {
    
    private int min;
    private int max;
    
    @Override
    public void initialize(ValidRange constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }
    
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        
        if (value < min || value > max) {
            // 自定义错误消息
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                String.format("参数值 %d 超出有效范围 [%d-%d]", value, min, max))
                .addConstraintViolation();
            return false;
        }
        
        return true;
    }
}