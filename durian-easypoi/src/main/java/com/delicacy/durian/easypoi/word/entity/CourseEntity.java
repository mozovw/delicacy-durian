package com.delicacy.durian.easypoi.word.entity;


import com.delicacy.durian.easypoi.excel.entity.StudentEntity;
import com.delicacy.durian.easypoi.excel.entity.TeacherEntity;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import org.jeecgframework.poi.excel.annotation.ExcelEntity;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;

import java.util.List;


/**
 * @Title: Entity
 * @Description: 课程
 * @author JueYue 2013-08-31 22:53:07
 * @version V1.0
 * 
 */
@Data
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
