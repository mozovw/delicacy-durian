package com.delicacy.durian.easypoi.excel.entity;

import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * 超链接测试
 * 
 * @author JueYue 2015年7月20日 下午10:35:20
 */
@Data
public class HyperLinkEntity {

	@Excel(name = "名称", isHyperlink = true)
	private String name;
	@Excel(name = "URL")
	private String url;

}
