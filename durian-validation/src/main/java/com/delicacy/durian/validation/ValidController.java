package com.delicacy.durian.validation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author yutao
 * @create 2020-03-11 13:47
 **/
@Slf4j
@RestController
@Validated
@RequestMapping("/a")
public class ValidController {
//    ObjectMapper objectMapper = new ObjectMapper();
//    @PostMapping("validUser")
//    public String validUser(@RequestBody @Valid User user, BindingResult bindingResult){
//        if(bindingResult.hasErrors()){
//            String string = null;
//            try {
//                string = objectMapper.writeValueAsString(bindingResult.getAllErrors());
//            } catch (JsonProcessingException e) {
//                e.printStackTrace();
//            }
//            log.error("error={}",string);
//            return "failure";
//        }
//        return "success";
//    }

    @PostMapping("validUser")
    public String validUser(@RequestBody @Valid User user) {
        return "success";
    }

    @PostMapping("validParams")
    public String validParams(@RequestParam("list") @NotEmpty(message = "集合不能为空") List<String> list,
                              @RequestParam("name") @NotBlank(message = "名称不能为空") String name) {
        return "success";
    }

    @PostMapping("validUserNotFound")
    public String validUserNotFound(@RequestBody User user) {
        throw new UserNotFoundException("用户不存在");
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public String handleConstraintViolationException(ConstraintViolationException e) {
        String msg = e.getConstraintViolations().stream().map(ee -> ee.getMessageTemplate()).collect(Collectors.joining(","));
        log.error("error={}", msg);
        return msg;
    }

    // 可用于全局异常处理
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
        log.error("error={}", e.getMessage());
        return e.getMessage();
    }

    // 可用于全局异常处理
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleValidationExceptions(
            MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        if (errors.isEmpty()) return null;
        String msg = errors.values().stream().collect(Collectors.joining(","));
        log.error("error={}", msg);
        return msg;
    }

    static class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String msg) {
            super(msg);
        }
    }
}
