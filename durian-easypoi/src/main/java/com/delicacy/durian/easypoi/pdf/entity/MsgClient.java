package com.delicacy.durian.easypoi.pdf.entity;

import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelEntity;

import java.util.Date;

@Data
public class MsgClient implements java.io.Serializable {
	/** id */
	private String id;
	// 电话号码(主键)
	@Excel(name = "电话号码")
	private String clientPhone = null;
	// 客户姓名
	@Excel(name = "姓名")
	private String clientName = null;
	// 所属分组
	@ExcelEntity
	private MsgClientGroup group = null;
	// 备注
	@Excel(name = "备注")
	private String remark = null;
	// 生日
	@Excel(name = "出生日期", format = "yyyy-MM-dd", width = 20)
	private Date birthday = null;
	// 创建人
	private String createBy = null;


}
