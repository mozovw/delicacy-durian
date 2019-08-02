package com.delicacy.durian.txmail.util;


import com.alibaba.fastjson.JSON;
import com.delicacy.durian.txmail.constant.TxMailConstant;
import com.delicacy.durian.txmail.dto.BaseResDto;
import com.delicacy.durian.txmail.dto.mailurl.TokenDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yutao
 * @create 2018-08-23 11:57
 **/
@Slf4j
public class TxMailUtil {

    private static String key  = "com.tomato.boss.sso.exam.TxMailUtil";

    public static BaseResDto getBaseResDto(Map<String, String> map, String url) {
        ResponseEntity<BaseResDto> forEntity = RestClientUtil.getRestTemplate().getForEntity(url, BaseResDto.class, map);
        if (forEntity.getBody().getErrcode().equals(TxMailConstant.ResResult.TOKEN_EXPIRE.getCode())) {
            String accessToken = getAccessToken(true);
            forEntity = RestClientUtil.getRestTemplate().getForEntity(url, BaseResDto.class, map);
        }
        return forEntity.getBody();
    }

    public static <T> BaseResDto getBaseResDto(String url, T t) {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<Object> entity = new HttpEntity<>(JSON.toJSONString(t), headers);
        Map<String, String> map = new HashMap<>();
        String accessToken = getAccessToken();
        map.put("access_token", accessToken);
        ResponseEntity<BaseResDto> forEntity = RestClientUtil.getRestTemplate().postForEntity(url, entity, BaseResDto.class, map);
        if (forEntity.getBody().getErrcode().equals(TxMailConstant.ResResult.TOKEN_EXPIRE.getCode())) {
            accessToken = getAccessToken(true);
            forEntity = RestClientUtil.getRestTemplate().postForEntity(url, entity, BaseResDto.class, map);
        }
        return forEntity.getBody();
    }

    public static String getAccessToken(){
        return getAccessToken(false);
    }

    private static String getAccessToken(boolean isforce) {
        //String s = RedisCacheUtil.get(key);
        String s = null;
        if (!isforce){
            if (StringUtils.isNotBlank(s) ) {
                return s;
            }
        }
        String corpid = TxMailConstant.CORPID;
        String corpsecret = TxMailConstant.ATTRLIST_CORPSECRET;
        Map<String, String> map = new HashMap<>();
        map.put("corpid", corpid);
        map.put("corpsecret", corpsecret);
        ResponseEntity<TokenDto> tokenResponse = RestClientUtil.getRestTemplate().getForEntity(TxMailConstant.GETTOKEN, TokenDto.class, map);
        if (tokenResponse.getBody().getErrcode() != TxMailConstant.ResResult.SUSSCESS.getCode()) {
            log.error("获取accessToken失败,error={}", tokenResponse.getBody().getErrmsg());
            throw new RuntimeException("获取accessToken失败");
        }
        String access_token = tokenResponse.getBody().getAccess_token();
        //RedisCacheUtil.set(key, access_token, 2 * 60 * 60+1);
        return access_token;
    }


}
