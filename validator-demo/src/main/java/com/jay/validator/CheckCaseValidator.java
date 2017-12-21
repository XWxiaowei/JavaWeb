package com.jay.validator;

import com.jay.validator.enums.CaseMode;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author xiang.wei
 * @create 2017/11/29 10:50
 */
public class CheckCaseValidator implements ConstraintValidator<CheckCase, String> {
    private CaseMode mode;
    @Override
    public void initialize(CheckCase checkCase) {
        this.mode = checkCase.value();
    }

    @Override
    public boolean isValid(String obj, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = false;
        if (obj == null) {
            return true;
        }
        if (mode == CaseMode.UPPER) {
            isValid= obj.equals(obj.toUpperCase());
        } else {
            isValid=obj.equals(obj.toLowerCase());
        }
        if (!isValid) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("{com.jay.validator.CheckCase.message}").addConstraintViolation();
        }
        return isValid;
    }
}
