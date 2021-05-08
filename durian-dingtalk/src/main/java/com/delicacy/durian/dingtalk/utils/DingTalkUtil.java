package com.delicacy.durian.dingtalk.utils;

import com.delicacy.durian.dingtalk.constants.DingTalkConstant;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.request.OapiMessageCorpconversationAsyncsendV2Request;
import com.dingtalk.api.request.OapiUserGetByMobileRequest;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.dingtalk.api.response.OapiMessageCorpconversationAsyncsendV2Response;
import com.dingtalk.api.response.OapiUserGetByMobileResponse;
import com.taobao.api.ApiException;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * 钉钉工作消息推送工具类
 *
 * @author yutao
 * @create 2020-04-20 11:00
 **/
@Slf4j
@UtilityClass
public class DingTalkUtil {

    /**
     * 根据手机号获取userid
     * @param token
     * @param mobile
     * @return userId
     */
    public String getUserId(String token, String mobile) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(DingTalkConstant.GETBUMOBILE_URL);
            OapiUserGetByMobileRequest request = new OapiUserGetByMobileRequest();
            request.setMobile(mobile);
            OapiUserGetByMobileResponse rsp = client.execute(request, token);
            if (!rsp.isSuccess()) {
                log.error("【根据手机号获取userid异常】errcode={},errmsg={}", rsp.getErrcode(), rsp.getErrmsg());
                return null;
            }
            return rsp.getUserid();
        } catch (ApiException e) {
            log.error("【根据手机号获取userid异常】errcode={},errmsg={}", e.getErrCode(), e.getErrMsg());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 发送企业会话消息
     * @param token
     * @param agentId
     * @param useridList
     * @param title
     * @param text
     * @param singleTitle
     * @param singleUrl
     * @return task_id
     */
    public Long sendCorpMsg(String token, Long agentId, String useridList, String title, String text,String singleTitle, String singleUrl) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(DingTalkConstant.ASYNCSEND_V2_URL);
            OapiMessageCorpconversationAsyncsendV2Request request = new OapiMessageCorpconversationAsyncsendV2Request();

            request.setUseridList(useridList);
            request.setAgentId(agentId);
            request.setToAllUser(false);
            // todo 整合msg
            OapiMessageCorpconversationAsyncsendV2Request.Msg msg = new OapiMessageCorpconversationAsyncsendV2Request.Msg();
//            msg.setMsgtype("link");
//            msg.setLink(new OapiMessageCorpconversationAsyncsendV2Request.Link());
//            msg.getLink().setTitle(title);
//            msg.getLink().setMarkdown(markdown);
//            msg.getLink().setMessageUrl(singleUrl);
//            msg.getLink().setPicUrl("test");
            msg.setMsgtype("action_card");
            msg.setActionCard(new OapiMessageCorpconversationAsyncsendV2Request.ActionCard());
            msg.getActionCard().setTitle(title);
            msg.getActionCard().setMarkdown(text);
            msg.getActionCard().setSingleTitle(singleTitle);
            msg.getActionCard().setSingleUrl(singleUrl);
            request.setMsg(msg);
            // todo 异步消息
            OapiMessageCorpconversationAsyncsendV2Response response = client.execute(request, token);
            if (!response.isSuccess()) {
                log.error("【企业会话消息异步发送接口异常】errcode={},errmsg={}", response.getErrcode(), response.getErrmsg());
                return null;
            }
            return response.getTaskId();
        } catch (ApiException e) {
            log.error("【企业会话消息异步发送接口异常】errcode={},errmsg={}", e.getErrCode(), e.getErrMsg());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取企业凭证
     *
     * @param appkey
     * @param appSecret
     * @return token
     */
    public String getToken(String appkey, String appSecret) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(DingTalkConstant.GETTOKEN_URL);
            OapiGettokenRequest req = new OapiGettokenRequest();
            req.setHttpMethod("GET");
            req.setAppkey(appkey);
            req.setAppsecret(appSecret);
            OapiGettokenResponse rsp = client.execute(req);
            if (!rsp.isSuccess()) {
                log.error("【获取企业凭证异常】errcode={},errmsg={}", rsp.getErrcode(), rsp.getErrmsg());
                return null;
            }
            return rsp.getAccessToken();
        } catch (ApiException e) {
            log.error("【获取企业凭证异常】errcode={},errmsg={}", e.getErrCode(), e.getErrMsg());
            e.printStackTrace();
            return null;
        }
    }
}
