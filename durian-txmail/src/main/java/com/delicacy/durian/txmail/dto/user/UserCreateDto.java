package com.delicacy.durian.txmail.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

/**
 * 用户创建dto
 *
 * @author yutao
 * @create 2018-08-17 11:54
 **/
@Getter
@Setter
@Builder
@NotBlank
@AllArgsConstructor
public class UserCreateDto implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 4601938979413154200L;
	private List<Long> department;	/*List<Integer>*/

    private List<String> slaves;	/*List<String>*/

    private String position;	/*产品经理*/

    private String tel;	/*123456*/

    @NotBlank
    private String name;	/*张三*/

    private Integer cpwd_login;	/*0*/
    @NotBlank
    private String userid;	/* zhangsan@gzdev.com */

    private String gender;	/*1*/

    private String extid;	/*01*/
    @JsonIgnore
    @NotBlank
    private String password;
    /********/
    private String mobile;	/*15913215XXX*/



}
