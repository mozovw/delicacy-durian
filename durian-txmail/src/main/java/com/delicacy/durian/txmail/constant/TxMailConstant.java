package com.delicacy.durian.txmail.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author yutao
 * @create 2018-08-17 11:09
 **/
public interface TxMailConstant {

    String CORPID = "wm063319043dabfbfa";
    String CORPSECRET = "dOk8yX2d2-aQXlgJ_ad67rYFhGLI2-wQRPcJKcvFaWNcVugYj2HXSbvruG9YFuJA";
    String ATTRLIST_CORPSECRET = "idwejCpLEMY9JWnvHLYIteY4iK_eiQlZgoXWYvIodY8eaDO-SZFDWCbz5rADQwqG";

    String GETTOKEN = "https://api.exmail.qq.com/cgi-bin/gettoken?corpid={corpid}&corpsecret={corpsecret}";
    String GET_LOGIN_URL = "https://api.exmail.qq.com/cgi-bin/service/get_login_url?access_token={access_token}&userid={userid}";

    @Getter
    @AllArgsConstructor
    enum ResResult {
        SUSSCESS(0, "邮箱操作成功"),
        TOKEN_EXPIRE(42001, "token已经过期");
        private Integer code;
        private String desc;
    }

    @Getter
    @AllArgsConstructor
    enum GroupDetailType {
        USER(Byte.valueOf("1")),
        DEPT(Byte.valueOf("2")),
        GROUP(Byte.valueOf("3"));
        private Byte code;
    }

    interface Dept {
        String CREATE = "https://api.exmail.qq.com/cgi-bin/department/create?access_token={access_token}";
        String UPDATE = "https://api.exmail.qq.com/cgi-bin/department/update?access_token={access_token}";
        String DELETE = "https://api.exmail.qq.com/cgi-bin/department/delete?access_token={access_token}&id={id}";
    }

    interface User {
        String CREATE = "https://api.exmail.qq.com/cgi-bin/user/create?access_token={access_token}";
        String UPDATE = "https://api.exmail.qq.com/cgi-bin/user/update?access_token={access_token}";
        String DELETE = "https://api.exmail.qq.com/cgi-bin/user/delete?access_token={access_token}&userid={userid}";
    }


    interface MailGroup {
        String CREATE = "https://api.exmail.qq.com/cgi-bin/group/create?access_token={access_token}";
        String UPDATE = "https://api.exmail.qq.com/cgi-bin/group/update?access_token={access_token}";
        String DELETE = "https://api.exmail.qq.com/cgi-bin/group/delete?access_token={access_token}&groupid={groupId}";
        String GET = "https://api.exmail.qq.com/cgi-bin/group/get?access_token={access_token}&groupId={groupId}";
    }

}
