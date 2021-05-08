package com.delicacy.durian.validation.error;

import org.springframework.http.HttpStatus;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zyt
 * @create 2020-05-09 16:12
 **/
public interface ExceptionHandler {
    void handle(Throwable throwable, Map map);
    Boolean isExceptionType(Object object);

    default void handle400ErrorAttributes(Map<String, Object> errorAttributes, Map<String, String> fromError) {
        if (fromError.isEmpty()) return;
        String msg = fromError.values().stream().collect(Collectors.joining(","));
        errorAttributes.put("message", msg);
        errorAttributes.put("details", fromError);
        errorAttributes.put("status", 400);
        errorAttributes.put("error", "Bad Request");

    }
}
