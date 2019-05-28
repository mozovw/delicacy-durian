package com.delicacy.durian.txmail.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 用户更改dto
 *
 * @author yutao
 * @create 2018-08-17 11:56
 **/
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDto implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = -7000463384086551629L;
	private List<Long> department;	/*List<Integer>*/
    private String position;	/*产品经理*/
    private String name;	/*张三*/
    private Integer cpwd_login;	/*1*/
    private Integer enable;	/*1*/
    @NotNull
    private String userid;	/* zhangsan@gzdev.com */
    private String gender;	/*1*/
    @JsonIgnore
    private String password;
    /********/
    private String mobile;	/*15913215421*/

}
