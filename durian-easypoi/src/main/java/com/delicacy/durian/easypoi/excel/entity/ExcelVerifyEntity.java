package com.delicacy.durian.easypoi.excel.entity;

import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@Data
public class ExcelVerifyEntity {

	/**
	 * Email校验
	 */
	@Excel(name = "Email", width = 25)
	private String email;
	/**
	 * 最大
	 */
	@Excel(name = "Max")
	@Max(value = 15, message = "max 最大值不能超过15")
	private int max;
	/**
	 * 最小
	 */
	@Excel(name = "Min")
	@Min(3)
	private int min;
	/**
	 * 非空校验
	 */
	@Excel(name = "NotNull")
	
	private String notNull;
	/**
	 * 正则校验
	 */
	@Excel(name = "Regex")
	@Pattern(regexp = "[\u4E00-\u9FA5]*", message = "不是中文")
	private String regex;


}
