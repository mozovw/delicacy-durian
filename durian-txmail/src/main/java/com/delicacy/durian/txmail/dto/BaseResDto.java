package com.delicacy.durian.txmail.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/***
 *
 * 基础返回dto
 * @author yutao
 *
 */
@Getter
@Setter
public class BaseResDto implements Serializable{

    private static final long serialVersionUID = -5369181086177097057L;
    private String errmsg;	/*created*/
    private Integer errcode;	/*0*/
    private Long id;

}
