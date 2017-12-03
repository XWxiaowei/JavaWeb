package com.jay.validator;

import com.jay.validator.enums.CaseMode;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import java.lang.annotation.RetentionPolicy;
/**
 * @author xiang.wei
 * @create 2017/11/29 10:24
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckCaseValidator.class)
@Documented
public @interface CheckCase {
    /**
     * 这个属性被用来定义默认消息模板
     *
     * @return
     */
    String message() default "{org.jay.validator.checkCase}";

    /**
     * 用来指定这个约束条件属于那些校验组
     *
     * @return
     */
    Class<?>[] groups() default {};

    /**
     * 这个属性不是API要求的，用来给约束条件指定严重级别
     *
     * @return
     */
    Class<? extends Payload>[] payload() default {};

    /**
     * 如果只有这个属性的话，可以忽略该属性的名称
     * eg:@CaseCheck(CaseMode.UPPER)
     *
     * @return
     */
    CaseMode value();
}
