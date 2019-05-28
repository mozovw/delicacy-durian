package com.delicacy.durian.txmail.util;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


/**
 * HTTP请求帮助类
 * @author yangzhilong
 *
 */
public class RestClientUtil {

    private static RestTemplate restTemplate;

    /**
     * 注入实现类
     * @param client
     */
    public static void setRestTemplate(RestTemplate client) {
    	restTemplate = client;
    }
    /**
     * 无参数或者参数附带在url中
     * @param url
     * @return
     */
    public static String get(String url) {
        return restTemplate.getForObject(url , String.class);
    }

    /**
     * 获取restTemple
     * @return
     */
    public static RestTemplate getRestTemplate(){
        return  restTemplate;
    }

    /**
     * json格式的post提交
     * @param obj
     * @param url
     * @return
     */
    public static String postJson(String url, Object obj) {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        String result = null;
        if(obj == null){
            result = "{}";
        }else{
            result = JSON.toJSONString(obj);
        }
        HttpEntity<String> formEntity = new HttpEntity<String>(result,headers);
        return restTemplate.postForObject(url , formEntity, String.class);
    }

    /**
     * form格式的post提交
     * @param map
     * @param url
     * @return
     */
    public static String postForm(String url, Map<String , String> map){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> params= new LinkedMultiValueMap<>();
        for(Map.Entry<String ,String> me : map.entrySet()){
            params.add(me.getKey() , me.getValue());
        }
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class).getBody();
    }

}
