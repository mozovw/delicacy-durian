package com.delicacy.durian.txmail.dto.mailgroup;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/***
 *
 * 邮件群组创建dto
 * @author yutao
 *
 */
@Getter
@Setter
public class MailGroupCreateDto {

    private List<String> userlist;	/*List<String>*/
    private List<String> grouplist;	/*List<String>*/
    private List<String> allow_userlist;	/*List<String>*/
    private List<Long> department;	/*List<Integer>*/
    @NotNull
    private Integer allow_type;	/*3*/
    @NotBlank
    private String groupname;	/*zhangsangroup*/
    @NotBlank
    private String groupid;	/*zhangsangroup@gzdev.com*/

}
