package com.delicacy.durian.validation;

import com.delicacy.durian.validation.error.BasicException;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author yutao
 * @create 2020-03-11 15:06
 **/
//@Component
public class ValidDefaultErrorAttributes extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest,
                                                  boolean includeStackTrace) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);
        if (errorAttributes.isEmpty()) return errorAttributes;
        Throwable error = getError(webRequest);
        if (error instanceof MethodArgumentNotValidException) {
            BindingResult bindingResult = ((MethodArgumentNotValidException) error).getBindingResult();
            Map<String, String> errorMap = new HashMap<>();
            bindingResult.getAllErrors().forEach((ee) -> {
                String fieldName = ((FieldError) ee).getField();
                String errorMessage = ee.getDefaultMessage();
                errorMap.put(fieldName, errorMessage);
            });
            handle400ErrorAttributes(errorAttributes, errorMap);
        } else if (error instanceof ConstraintViolationException) {
            Set<ConstraintViolation<?>> constraintViolations = ((ConstraintViolationException) error).getConstraintViolations();
            if (constraintViolations.isEmpty()) return errorAttributes;
            Map<String, String> errorMap = new HashMap<>();
            constraintViolations.stream().forEach(ee -> {
                String fieldName = ((PathImpl) ee.getPropertyPath()).getLeafNode().getName();
                String errorMessage = ee.getMessageTemplate();
                errorMap.put(fieldName, errorMessage);
            });
            handle400ErrorAttributes(errorAttributes, errorMap);
        }else if(error instanceof BasicException){
            errorAttributes.put("code", ((BasicException) error).getCode());
        }
        return errorAttributes;
    }

    private void handle400ErrorAttributes(Map<String, Object> errorAttributes, Map<String, String> fromError) {
        if (fromError.isEmpty()) return;
        String msg = fromError.values().stream().collect(Collectors.joining(","));
        errorAttributes.put("message", msg);
        errorAttributes.put("details", fromError);
        errorAttributes.put("status", 400);
        errorAttributes.put("error", "Bad Request");
    }

}
