package com.delicacy.durian.validation.validator;

import com.delicacy.durian.validation.anotation.NotListEmpty;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * 自定义注解ListNotHasNull 的实现类
 * 用于判断List集合中是否含有null元素
 */
public class NotListEmptyValidator implements ConstraintValidator<NotListEmpty, List> {

    private int value;

    @Override
    public void initialize(NotListEmpty constraintAnnotation) {
        //传入value 值，可以在校验中使用
        this.value = constraintAnnotation.value();
    }

    public boolean isValid(List list, ConstraintValidatorContext constraintValidatorContext) {
        if (list==null||list.size()==0)return false;
        return list.stream().anyMatch(e->e!=null);
    }

}