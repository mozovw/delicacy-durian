package com.delicacy.durian.txmail.dto.mailurl;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UrlDto implements Serializable {
    private static final long serialVersionUID = 6690500298913115446L;
    private Integer errcode;
    private String errmsg;
    private String login_url;
    private String expires_in;
}