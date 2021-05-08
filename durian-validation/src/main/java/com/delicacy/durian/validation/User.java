package com.delicacy.durian.validation;

import com.delicacy.durian.validation.anotation.NotListEmpty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.awt.print.Book;
import java.util.List;

@Data
public class User {

    @NotBlank(message = "名称不能为空")
    private String name;

    @NotNull(message = "年龄不能为空")
    private Integer age;

    @NotBlank(message = "邮箱不能为空")
    @Email(regexp = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$", message = "邮箱格式不正确")
    private String email;

    @NotListEmpty(message = "书籍不能为空")
    private List<Book> books;

    @NotEmpty(message = "昵称不能为空")
    private List<String> nicknames;

}