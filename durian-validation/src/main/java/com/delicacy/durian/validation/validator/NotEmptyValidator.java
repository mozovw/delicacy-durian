package com.delicacy.durian.validation.validator;



import com.delicacy.durian.validation.anotation.NotEmpty2;
import org.springframework.util.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotEmptyValidator implements ConstraintValidator<NotEmpty2, Object> {

    private String message;

    @Override
    public void initialize(NotEmpty2 constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object list, ConstraintValidatorContext constraintValidatorContext) {
        return !ObjectUtils.isEmpty(list);
    }

}