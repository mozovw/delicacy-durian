package com.delicacy.durian.txmail.dto.dept;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * 部门更改参数dto
 *
 * @author yutao
 * @create 2018-08-17 11:48
 **/
@Getter
@Setter
public class DeptUpdateDto {
    @NotNull
    private Long id;	/*2*/

    private Integer order;	/*0*/

    private String name;	/*广州研发中心*/

    private Long parentid;	/*1*/

}
