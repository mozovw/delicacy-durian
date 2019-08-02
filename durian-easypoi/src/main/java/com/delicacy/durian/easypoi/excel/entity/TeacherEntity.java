package com.delicacy.durian.easypoi.excel.entity;

import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;

@Data
@SuppressWarnings("serial")
@ExcelTarget("teacherEntity")
public class TeacherEntity implements java.io.Serializable {
	/** id */
	@Excel(name = "老师ID_teacherEntity,老师属性_courseEntity", orderNum = "2", needMerge = false, isImportField = "true_teacherEntity,true_courseEntity")
	private String id;
	/** name */
	@Excel(name = "老师姓名_yuwen,数学老师_shuxue", orderNum = "2", mergeVertical = true, isImportField = "true_yuwen")
	private String name;
}
