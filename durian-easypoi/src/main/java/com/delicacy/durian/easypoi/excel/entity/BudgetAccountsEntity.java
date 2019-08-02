package com.delicacy.durian.easypoi.excel.entity;

import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;

@Data
public class BudgetAccountsEntity {
	@Excel(name = "编码")
	private String code;

	@Excel(name = "名称")
	private String name;

}
