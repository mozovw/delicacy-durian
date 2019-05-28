package com.delicacy.durian.txmail.dto.mailgroup;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

/***
 *
 * 邮件群组更改dto
 * @author yutao
 *
 */
@Getter
@Setter
public class MailGroupUpdateDto {

    private List<String> userlist;	/*List<String>*/
    private List<String> grouplist;	/*List<String>*/
    private List<String> allow_userlist;	/*List<String>*/
    private List<Long> department;	/*List<Integer>*/
    private Integer allow_type;	/*3*/
    private String groupname;	/*zhangsangroup*/
    @NotBlank
    private String groupid;	/*zhangsangroup@gzdev.com*/



}
