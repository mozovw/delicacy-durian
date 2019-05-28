package com.delicacy.durian.txmail.service;


import com.alibaba.fastjson.JSON;
import com.delicacy.durian.txmail.constant.TxMailConstant;
import com.delicacy.durian.txmail.dto.BaseResDto;
import com.delicacy.durian.txmail.dto.user.UserCreateDto;
import com.delicacy.durian.txmail.dto.user.UserUpdateDto;
import com.delicacy.durian.txmail.util.TxMailUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.delicacy.durian.txmail.util.TxMailUtil.getAccessToken;

/**
 * @author yutao
 * @create 2018-08-17 14:57
 **/
@Service
@Slf4j
public class MailUserService {

    public BaseResDto createUser(UserCreateDto groupCreateDto) {
        log.info("入参:{}", JSON.toJSONString(groupCreateDto));
        return TxMailUtil.getBaseResDto(TxMailConstant.User.CREATE, groupCreateDto);
    }

    public BaseResDto deleteUser(String userid) {
        log.info("入参:{}", JSON.toJSONString(userid));
        Map<String, String> map = new HashMap<>();
        String accessToken = getAccessToken();
        map.put("access_token", accessToken);
        map.put("userid", userid);
        return TxMailUtil.getBaseResDto(map, TxMailConstant.User.DELETE);
    }

    public BaseResDto updateUser(UserUpdateDto userUpdateDto) {
        log.info("入参:{}", JSON.toJSONString(userUpdateDto));
        return TxMailUtil.getBaseResDto(TxMailConstant.User.UPDATE, userUpdateDto);
    }

    /*private <T> ResponseEntity<BaseResDto> getPostResult(String url, T t) {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<Object> entity = new HttpEntity<>(JSON.toJSONString(t), headers);
        Map<String, String> map = new HashMap<>();
        String accessToken = getAccessToken();
        map.put("access_token", accessToken);
        return RestClientUtil.getRestTemplate().postForEntity(url, entity, BaseResDto.class, map);
    }


    private String getAccessToken() {
        String corpid = TxMailConstant.CORPID;
        String corpsecret = TxMailConstant.ATTRLIST_CORPSECRET;
        Map<String, String> map = new HashMap<>();
        map.put("corpid", corpid);
        map.put("corpsecret", corpsecret);
        ResponseEntity<TokenDto> tokenResponse = RestClientUtil.getRestTemplate().getForEntity(TxMailConstant.GETTOKEN, TokenDto.class, map);
        return tokenResponse.getBody().getAccess_token();
    }*/

}
