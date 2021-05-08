package com.delicacy.durian.validation.error;

import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author yutao
 * @create 2020-05-09 16:13
 **/
@Component
public class ConstraintViolationExceptionHandler implements ExceptionHandler {
    @Override
    public void handle(Throwable error, Map map) {
        Set<ConstraintViolation<?>> constraintViolations = ((ConstraintViolationException) error).getConstraintViolations();
        if (constraintViolations.isEmpty()) return;
        Map<String, String> errorMap = new HashMap<>();
        constraintViolations.stream().forEach(ee -> {
            String fieldName = ((PathImpl) ee.getPropertyPath()).getLeafNode().getName();
            String errorMessage = ee.getMessageTemplate();
            errorMap.put(fieldName, errorMessage);
        });
        handle400ErrorAttributes(map, errorMap);
    }

    @Override
    public Boolean isExceptionType(Object object) {
        return object != null && object instanceof ConstraintViolationException;
    }
}
