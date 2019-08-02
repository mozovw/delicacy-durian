package com.delicacy.durian.easypoi.excel.entity;

import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;

import java.math.BigDecimal;

@Data
public class StatisticEntity {
 
	@Excel(name = "名称")
	private String name;

	@Excel(name = "int 测试", width = 15, isStatistics = true)
	private int intTest;

	@Excel(name = "long 测试", width = 15, isStatistics = true)
	private long longTest;

	@Excel(name = "double 测试", width = 15, isStatistics = true)
	private double doubleTest;

	@Excel(name = "string 测试", width = 15, isStatistics = true)
	private String stringTest;

	@Excel(name = "BigDecimal 测试", width = 15, isStatistics = true)
	private BigDecimal bigDecimalTest;

}
