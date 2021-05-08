package com.delicacy.durian.validation.error;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yutao
 * @create 2020-05-09 16:13
 **/
@Component
public class MethodArgumentNotValidExceptionHandler implements ExceptionHandler {
    @Override
    public void handle(Throwable error, Map map) {
        BindingResult bindingResult = ((MethodArgumentNotValidException) error).getBindingResult();
        Map<String, String> errorMap = new HashMap<>();
        bindingResult.getAllErrors().forEach((ee) -> {
            String fieldName = ((FieldError) ee).getField();
            String errorMessage = ee.getDefaultMessage();
            errorMap.put(fieldName, errorMessage);
        });
        handle400ErrorAttributes(map, errorMap);

    }

    @Override
    public Boolean isExceptionType(Object object) {
        return object != null && object instanceof MethodArgumentNotValidException;
    }
}
