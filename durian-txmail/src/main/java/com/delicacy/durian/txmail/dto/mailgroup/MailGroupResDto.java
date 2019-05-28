package com.delicacy.durian.txmail.dto.mailgroup;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/***
 *
 * 邮件群组返回dto
 * @author yutao
 *
 */
@Getter
@Setter
public class MailGroupResDto {

    private List<String> userlist;	/*List<String>*/
    private List<String> grouplist;	/*List<String>*/
    private List<String> allow_userlist;	/*List<String>*/
    private List<Integer> department;	/*List<Integer>*/
    private Integer allow_type;	/*3*/
    private String groupname;	/*zhangsangroup*/
    private String groupid;	/*zhangsangroup@gzdev.com*/
    private String errmsg;	/*ok*/
    private Integer errcode;	/*0*/


}
