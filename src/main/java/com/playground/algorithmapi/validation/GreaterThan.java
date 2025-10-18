package com.playground.algorithmapi.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ElementType.TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GreaterThanValidator.class)
@Documented
@Repeatable(GreaterThan.List.class)
public @interface GreaterThan {
    String message() default "字段A必须大于字段B";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    
    /**
     * 被比较的字段名（A字段）
     */
    String fieldA();
    
    /**
     * 比较对象字段名（B字段）
     */
    String fieldB();
    
    /**
     * 自定义错误消息模板
     */
    String customMessage() default "{fieldA}必须大于{fieldB}";
    
    /**
     * 支持重复注解的容器
     */
    @Target({ElementType.TYPE, ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        GreaterThan[] value();
    }
}