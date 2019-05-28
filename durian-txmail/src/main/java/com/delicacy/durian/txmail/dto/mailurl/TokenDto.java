package com.delicacy.durian.txmail.dto.mailurl;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class TokenDto implements Serializable {
    private static final long serialVersionUID = -3955775442218409699L;
    private Integer errcode;
    private String errmsg;
    private String access_token;
    private String expires_in;
}