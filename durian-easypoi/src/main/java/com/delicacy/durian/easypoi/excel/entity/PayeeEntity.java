package com.delicacy.durian.easypoi.excel.entity;

import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;

@Data
public class PayeeEntity {
	@Excel(name = "全称")
	private String name;

	@Excel(name = "银行账号")
	private String bankAccount;

	@Excel(name = "开户银行")
	private String bankName;
}
