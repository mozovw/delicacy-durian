package com.delicacy.durian.validation;

import com.delicacy.durian.validation.anotation.NotListEmpty;
import com.delicacy.durian.validation.util.Jsr303Util;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@RestController
@SpringBootApplication
@ComponentScan("com.delicacy")
public class DurianValidationApplication {

    public static void main(String[] args) {
        SpringApplication.run(DurianValidationApplication.class, args);
    }

    private ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("sendMessage")
    public String sendMessage() {
        User user = new User();
        List<Book> books = new ArrayList<>();
        books.add(null);
        user.setBooks(books);
        user.setEmail("sss");
        String check = Jsr303Util.check(user);
        return check;
    }

    @PostMapping("validUser")
    private Boolean validUser(@RequestBody @Valid User user, BindingResult bindingResult) throws JsonProcessingException {
        if(bindingResult.hasErrors()){
           String string = objectMapper.writeValueAsString(bindingResult.getAllErrors());
           log.error("error={}",string);
           return false;
        }
        return true;
    }

    @Data
    //@JsonInclude(JsonInclude.Include.NON_NULL)
    public static class User {

        @NotBlank
        private String name;

        @NotNull
        private Integer age;

        @Email(regexp = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$")
        private String email;

        @NotListEmpty
        private List<Book> books;

        @NotEmpty
        private List<String> nicknames;

    }
}
