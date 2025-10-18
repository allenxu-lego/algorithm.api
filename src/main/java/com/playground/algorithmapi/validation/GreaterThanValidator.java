package com.playground.algorithmapi.validation;

import java.lang.reflect.Field;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class GreaterThanValidator implements ConstraintValidator<GreaterThan, Object> {
    
    private String fieldA;
    private String fieldB;
    private String customMessage;
    
    @Override
    public void initialize(GreaterThan constraintAnnotation) {
        this.fieldA = constraintAnnotation.fieldA();
        this.fieldB = constraintAnnotation.fieldB();
        this.customMessage = constraintAnnotation.customMessage();
    }
    
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        if (obj == null) {
            return true;
        }
        
        try {
            Object valueA = getFieldValue(obj, fieldA);
            Object valueB = getFieldValue(obj, fieldB);
            
            if (valueA == null || valueB == null) {
                return true; // 让其他校验处理null值
            }
            
            // 支持数字类型的比较
            if (valueA instanceof Number && valueB instanceof Number) {
                double numA = ((Number) valueA).doubleValue();
                double numB = ((Number) valueB).doubleValue();
                
                if (numA <= numB) {
                    // 自定义错误消息
                    context.disableDefaultConstraintViolation();
                    String message = customMessage
                        .replace("{fieldA}", fieldA)
                        .replace("{fieldB}", fieldB);
                    context.buildConstraintViolationWithTemplate(
                        String.format("%s (当前值: %s <= %s)", message, valueA, valueB))
                        .addConstraintViolation();
                    return false;
                }
                return true;
            }
            
            // 支持Comparable类型的比较
            if (valueA instanceof Comparable && valueB instanceof Comparable) {
                @SuppressWarnings("unchecked")
                Comparable<Object> compA = (Comparable<Object>) valueA;
                
                if (compA.compareTo(valueB) <= 0) {
                    context.disableDefaultConstraintViolation();
                    String message = customMessage
                        .replace("{fieldA}", fieldA)
                        .replace("{fieldB}", fieldB);
                    context.buildConstraintViolationWithTemplate(
                        String.format("%s (当前值: %s <= %s)", message, valueA, valueB))
                        .addConstraintViolation();
                    return false;
                }
                return true;
            }
            
            return true; // 不支持的类型默认通过
            
        } catch (Exception e) {
            // 反射异常，记录日志但不阻止校验
            return true;
        }
    }
    
    private Object getFieldValue(Object obj, String fieldName) throws Exception {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(obj);
    }
}