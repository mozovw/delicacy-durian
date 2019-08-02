package com.delicacy.durian.easypoi.excel.entity;

import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import org.jeecgframework.poi.excel.annotation.ExcelEntity;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;

import java.util.List;

@Data
@SuppressWarnings("serial")
@ExcelTarget("courseEntity")
public class CourseEntity implements java.io.Serializable {
	/** 主键 */
	private String id;
	/** 课程名称 */
	@Excel(name = "课程名称", orderNum = "1", width = 25, needMerge = true, isImportField = "true")
	private String name;
	/** 老师主键 */
	@ExcelEntity(id = "yuwen")
	private TeacherEntity teacher;
	/** 老师主键 */
	// @ExcelEntity(id = "shuxue")
	private TeacherEntity shuxueteacher;

	@ExcelCollection(id = "st", name = "学生", orderNum = "4")
	private List<StudentEntity> students;

}
