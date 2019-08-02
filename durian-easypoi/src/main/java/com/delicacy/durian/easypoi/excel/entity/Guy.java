package com.delicacy.durian.easypoi.excel.entity;

import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;

@Data
public class Guy {
	@Excel(name = "电话号码")
	private String tel;
	@Excel(name = "姓名")
	private String name;
	@Excel(name = "分组")
	private String group;
	@Excel(name = "备注")
	private String remark;
	@Excel(name = "出生日期")
	private String birthday;
}
