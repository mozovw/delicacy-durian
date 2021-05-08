package com.delicacy.durian.validation.error;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yutao
 * @create 2020-03-11 15:06
 **/
@Component
public class DefaultErrorAttributesProcessor extends DefaultErrorAttributes implements BeanPostProcessor {
//    List<ExceptionHandler> list = Collections.synchronizedList(new ArrayList());
    ConcurrentHashMap<String,ExceptionHandler> concurrentHashMap  = new ConcurrentHashMap<>();

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest,
                                                  boolean includeStackTrace) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);
        if (errorAttributes.isEmpty()) return errorAttributes;
        Throwable error = getError(webRequest);

        Collection<ExceptionHandler> values = concurrentHashMap.values();
        values.stream().forEach(e->{
            if (e.isExceptionType(error)){
                webRequest.setAttribute("javax.servlet.error.status_code", HttpStatus.BAD_REQUEST.value(),0);
                e.handle(error,errorAttributes);
            }
        });
        return errorAttributes;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof  ExceptionHandler){
            concurrentHashMap.put(beanName,(ExceptionHandler) bean);
        }
        return bean;
    }
}
