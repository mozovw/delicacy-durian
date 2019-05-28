package com.delicacy.durian.txmail.dto.dept;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 部门创建dto
 *
 * @author yutao
 * @create 2018-08-17 11:50
 **/
@Getter
@Setter
public class DeptCreateDto {
    private	Integer	order;	/*0*/
    @NotBlank
    private	String	name;	/*广州研发中心*/
    @NotNull
    private	Long	parentid;	/*1*/


}
