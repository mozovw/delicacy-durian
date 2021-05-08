package com.delicacy.durian.validation;

import com.delicacy.durian.validation.anotation.NotEmpty2;
import com.delicacy.durian.validation.error.BasicException;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yutao
 * @create 2020-03-11 13:47
 **/
@Slf4j
@RestController
@Validated
@RequestMapping("/b")
public class Valid2Controller {
    @PostMapping("validUser")
    public String validUser(@RequestBody @Valid User user){
        return "success";
    }

    @PostMapping("validUser2")
    public String validUser2(@RequestBody @Valid User2 user){
        return "success";
    }

    @PostMapping("validParams")
    public Map<String,Object> validParams(
                              @RequestParam("str") @NotEmpty2(message = "集合2不能为空") String str
                             ) {
        Map<String,Object> map = new HashMap<>();
        map.put("success",true);

        return map; }

    @PostMapping("validUserNotFound")
    public String validUserNotFound(@RequestBody User user){
        throw new PromptException("用户不存在。");
    }

    @PostMapping("logoutByUserInvalid")
    public String logoutByUserInvalid(@RequestBody User user){
        throw new LogoutException("此用户失效，退出！");
    }

    // reason 会覆盖 message
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR/*,reason = "用户不存在"*/)
    static class PromptException extends BasicException {
        public PromptException(String message) {
            super(5000,message);
        }
    }

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    static class OperationException extends BasicException{
        public OperationException(Integer code,String message) {
            super(code,message);
        }
    }

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    static class LogoutException extends OperationException{
        public LogoutException() {
            super(5001,"退出！");
        }
        public LogoutException(String message) {
            super(5001,message);
        }
    }


    
}
